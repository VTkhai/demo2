package com.example.demo2.controller.authentication;

import com.example.demo2.model.response.AuthenticationResponse;
import com.example.demo2.model.request.authentication.AuthenticationRequest;
import com.example.demo2.model.request.authentication.RegisterRequest;
import com.example.demo2.service.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private final LogoutHandler logoutHandler;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping(path = "/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    )throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            logoutHandler.logout(request, response, authentication); // Gọi logoutHandler
            SecurityContextHolder.clearContext(); // Xóa ngữ cảnh bảo mật
        }
        return ResponseEntity.ok("Logout successful");
    }
}
