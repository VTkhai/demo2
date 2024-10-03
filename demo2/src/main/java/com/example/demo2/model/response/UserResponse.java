package com.example.demo2.model.response;

import com.example.demo2.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long idUser;

    private String firstname;

    private String lastname;

    private String email;

    private Role role;
}