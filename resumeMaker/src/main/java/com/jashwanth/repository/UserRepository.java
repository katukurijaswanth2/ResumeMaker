package com.jashwanth.repository;

import com.jashwanth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long > {
    //Find user by username (useful for login)

    Optional<User> findByUsername(String username);

    //Find user by email (useful for login / password reset)

    Optional<User> findByEmail(String email);

    //Check if username already exists

    boolean existsByUsername(String username);

    //Check if email already exists

    boolean existsByEmail(String email);
}
