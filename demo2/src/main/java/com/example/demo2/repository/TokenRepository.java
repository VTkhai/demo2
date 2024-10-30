package com.example.demo2.repository;

import com.example.demo2.entity.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query(value = """
      select t from Token t inner join User u
      on t.user.idUser = u.idUser
      where u.idUser = :idUser and (t.expired = false or t.revoked = false)
      """)
    List<Token> findAllValidTokenByUser(Long idUser);

    Optional<Token> findByToken (String token);
}
