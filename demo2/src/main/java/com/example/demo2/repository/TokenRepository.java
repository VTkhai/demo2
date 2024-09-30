//package com.example.demo2.repository;
//
//import com.example.demo2.entity.token.Token;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface TokenRepository extends JpaRepository<Token, Long> {
//    @Query("""
//    SELECT t.*
//             FROM Token t
//             INNER JOIN User u ON t.id_user = u.idUser
//             WHERE u.idUser = :userId AND (t.expired = false OR t.revoked = false);
//   """)
//    List<Token> findAllValidTokenByUser (Integer userId);
//    Optional<Token> findByToken (String token);
//}
