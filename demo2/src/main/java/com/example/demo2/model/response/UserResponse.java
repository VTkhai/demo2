package com.example.demo2.model.response;

import com.example.demo2.entity.authentication.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotEmpty(message = "Firstname cannot be empty")
    @Column(length = 20)
    private String firstname;

    @NotEmpty(message = "Lastname cannot be empty")
    @Column(length = 20)
    private String lastname;

    @NotEmpty(message = "Password cannot be empty")
    @Column(length = 20)
    private String password;

    @NotEmpty(message = "Email cannot be empty")
    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}