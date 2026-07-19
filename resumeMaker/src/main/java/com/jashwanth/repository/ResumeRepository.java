package com.jashwanth.repository;

import com.jashwanth.entity.Resume;
import com.jashwanth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume , Long> {
    //Find all resumes belonging to a specific user

    List<Resume> findByUser(User user);

    //Find all resumes by user ID (most commonly used)

    List<Resume> findByUserId(Long userId);

    //Find a specific resume by ID and ensure it belongs to the user (security best practice)

    Optional<Resume> findByIdAndUserId(Long id, Long userId);

    //Find resume by email (since email is unique in the table)

    Optional<Resume> findByEmail(String email);

    //Check if a resume with given email exists

    boolean existsByEmail(String email);
}
