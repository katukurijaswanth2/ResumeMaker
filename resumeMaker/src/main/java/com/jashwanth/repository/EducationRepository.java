package com.jashwanth.repository;

import com.jashwanth.entity.Education;
import com.jashwanth.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education , Long> {
    //Find all education entries for a specific resume

    List<Education> findByResume(Resume resume);

    //Find all education entries by resume ID (most commonly used)

    List<Education> findByResumeId(Long resumeId);

    //Find education by ID and ensure it belongs to the resume (security)

    Optional<Education> findByIdAndResumeId(Long id, Long resumeId);

    //Find all education for a resume, ordered by start date (newest first)

    List<Education> findByResumeIdOrderByStartDateDesc(Long resumeId);

    //Optional: Delete all education entries for a resume

    void deleteByResumeId(Long resumeId);
}
