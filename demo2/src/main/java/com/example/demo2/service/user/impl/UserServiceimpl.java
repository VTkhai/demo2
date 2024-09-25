package com.example.demo2.service.user.impl;

import com.example.demo2.entity.authentication.User;
import com.example.demo2.entity.supplier.Supplier;
import com.example.demo2.exception.ResourceNotFoundException;
import com.example.demo2.mapper.SupplierMapper;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.model.request.authentication.UserRequest;
import com.example.demo2.model.resource.SupplierResource;
import com.example.demo2.model.response.SupplierResponse;
import com.example.demo2.model.response.UserResponse;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse updateUser(Long idUser, UserRequest userRequest) {
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with ID " + idUser + " not found."));
        UserMapper.INSTANCE.updateUserFromRequest(userRequest, existingUser);

        // Lưu User đã cập nhật vào cơ sở dữ liệu
        User updatedUser = userRepository.save(existingUser);

        // Trả về phản hồi dưới dạng UserResponse
        return new UserResponse();
    }

    @Override
    public void deleteUser(Long idUser) {
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with ID " + idUser + " not found."));
        userRepository.delete(existingUser);
    }

    @Override
    public UserResponse getUserDetails(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with ID " + idUser + " not found."));
        UserResponse userResponse =  UserMapper.INSTANCE.toResponse(user);
        return new UserResponse();
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {UserResponse userResponse
                        = UserMapper.INSTANCE.toResponse(user);
                    return new UserResponse();
                })
                .collect(Collectors.toList());
    }

}
