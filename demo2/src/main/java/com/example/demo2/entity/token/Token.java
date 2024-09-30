//package com.example.demo2.entity.token;
//
//import com.example.demo2.entity.authentication.User;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter
//@Setter
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "token_entity")
//public class Token {
//    @Id
//    @GeneratedValue
//    private  Long idToken;
//
//    private String token;
//
//    @Enumerated(EnumType.STRING)
//    private TokenType tokenType;
//
//    private boolean expired;
//
//    private boolean revoked;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_user")
//    private User user;
//}
