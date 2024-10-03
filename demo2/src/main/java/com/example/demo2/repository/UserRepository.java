package com.example.demo2.repository;

import com.example.demo2.entity.user.Role;
import com.example.demo2.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User>findByLastnameOrFirstname(String firstname, String lastname);
    List<User> findByRole(Role role);
}
