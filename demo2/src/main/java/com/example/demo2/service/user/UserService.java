package com.example.demo2.service.user;

import com.example.demo2.entity.user.Role;
import com.example.demo2.model.request.authentication.RegisterRequest;
import com.example.demo2.model.request.user.ChangePasswordRequest;
import com.example.demo2.model.response.UserResponse;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;

@Service
public interface UserService {
    UserResponse updateUser(Long idUser, RegisterRequest userRequest);
    void deleteUser(Long idUser);
    UserResponse getUserDetails (Long idUser);
    List<UserResponse> getAllUser();
    List<UserResponse> searchUsers(String firstname, String lastname);
    List<UserResponse> searchByRole(Role role);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
