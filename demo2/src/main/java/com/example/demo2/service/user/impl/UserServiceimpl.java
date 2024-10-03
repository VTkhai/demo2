package com.example.demo2.service.user.impl;

import com.example.demo2.entity.user.Role;
import com.example.demo2.entity.user.User;
import com.example.demo2.exception.ResourceNotFoundException;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.model.request.authentication.RegisterRequest;
import com.example.demo2.model.request.user.ChangePasswordRequest;
import com.example.demo2.model.response.UserResponse;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse updateUser(Long idUser, RegisterRequest userRequest) {
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with ID " + idUser + " not found."));
        UserMapper.INSTANCE.updateUserFromRequest(userRequest, existingUser);

        // Lưu User đã cập nhật vào cơ sở dữ liệu
        User updatedUser = userRepository.save(existingUser);

        // Trả về phản hồi dưới dạng UserResponse
        return UserMapper.INSTANCE.toResponse(updatedUser);
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
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + idUser + " not found."));
        return UserMapper.INSTANCE.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> searchUsers(String firstname, String lastname) {
        List<User> users;

        //FIXME null vs empty
        if (StringUtils.isNotEmpty(firstname) || StringUtils.isNotEmpty(lastname))  {
           users = userRepository.findByLastnameOrFirstname(firstname, lastname);
        } else {
            return null;
        }

        return users.stream()
                .map(UserMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> searchByRole(Role role) {
        List<User> users = userRepository.findByRole(role);
        return users.stream()
                .map(UserMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        //Check the current password is correct
       if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
           throw new IllegalArgumentException("Wrong Password");
       }
       // Check new password are the same
       if(!request.getNewPassword().equals(request.getConfirmPassword())){
           throw  new IllegalArgumentException("Password are not the same!");
       }
       user.setPassword(passwordEncoder.encode(request.getNewPassword()));
       userRepository.save(user);
    }
}
