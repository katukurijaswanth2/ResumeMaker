package com.jashwanth.repository;

import com.jashwanth.entity.Experience;
import com.jashwanth.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience , Long> {
    //Find all experiences for a specific resume

    List<Experience> findByResume(Resume resume);

    //Find all experiences by resume ID (most commonly used)

    List<Experience> findByResumeId(Long resumeId);

    //Find experience by ID and resume ID (security/ownership check)

    Optional<Experience> findByIdAndResumeId(Long id, Long resumeId);

    //Find all experiences for a resume, ordered by start date (newest first)
    List<Experience> findByResumeIdOrderByStartDateDesc(Long resumeId);

    //Optional: Find currently working experiences
    List<Experience> findByResumeIdAndCurrentlyWorkingTrue(Long resumeId);

    //Delete all experiences for a resume
    void deleteByResumeId(Long resumeId);
}
