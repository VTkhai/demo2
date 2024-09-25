package com.example.demo2.model.request.authentication;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
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
}