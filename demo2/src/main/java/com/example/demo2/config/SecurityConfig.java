package com.example.demo2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.demo2.entity.user.Permission.*;
import static com.example.demo2.entity.user.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/auth/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST_URL).permitAll()// Allow access to API documentation
                        .requestMatchers("/Suppliers/**", "/Products/**")
                        .hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers(GET,"/Suppliers/**", "/Products/**" )
                        .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(POST,"/Suppliers/**", "/Products/**" )
                        .hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                        .requestMatchers(PUT,"/Suppliers/**", "/Products/**" )
                        .hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                        .requestMatchers(DELETE,"/Suppliers/**", "/Products/**" )
                        .hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                        .requestMatchers("/User/**")
                        .hasRole(ADMIN.name())
                        .requestMatchers(GET,"/User/**" )
                        .hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST,"/User/**" )
                        .hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT,"/User/**")
                        .hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE,"/User/**" )
                        .hasAuthority(ADMIN_DELETE.name())
                        .anyRequest().authenticated()// Require authentication for all other requests
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session management
                )
                .authenticationProvider(authenticationProvider) // Set your authentication provider
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout") // URL sau khi logout thành công
                                .invalidateHttpSession(true) // Hủy phiên làm việc hiện tại
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ; // Add JWT filter

        return http.build();
    }

}
