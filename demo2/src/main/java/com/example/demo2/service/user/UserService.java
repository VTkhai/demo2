package com.example.demo2.service.user;

import com.example.demo2.model.request.authentication.UserRequest;
import com.example.demo2.model.response.UserResponse;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface UserService {
    UserResponse updateUser(Long idUser, UserRequest userRequest);
    void deleteUser(Long idUser);
    UserResponse getUserDetails (Long idUser);
    List<UserResponse> getAllUser();

//    List<UserResponse> searchSuppliers(String supplierName);
}
