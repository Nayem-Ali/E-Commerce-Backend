package com.ecommerce.ecommerce.repository.auth;

import com.ecommerce.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailAndPassword(String email, String password);
    Users findUserByEmail(String email);
}
