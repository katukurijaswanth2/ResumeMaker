package com.jashwanth.service;

import com.jashwanth.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(String username, String email, String rawPassword);

    User login(String usernameOrEmail, String rawPassword);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(Long id, String username, String email);

    void changePassword(Long id, String oldPassword, String newPassword);

    void deleteUser(Long id);

    boolean usernameExists(String username);

    boolean emailExists(String email);
}
