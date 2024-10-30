package com.example.demo2.entity.token;

import com.example.demo2.entity.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_entity")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idToken;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    @Column(name = "expired", columnDefinition = "TINYINT(1)")
    private boolean expired;

    @Column(name = "revoked", columnDefinition = "TINYINT(1)")
    private boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
}
