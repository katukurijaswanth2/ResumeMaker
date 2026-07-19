package com.jashwanth.repository;

import com.jashwanth.entity.Resume;
import com.jashwanth.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill , Long> {
    //Find all skills for a specific resume
    List<Skill> findByResume(Resume resume);

    //Find all skills by resume ID (most commonly used)

    List<Skill> findByResumeId(Long resumeId);

    //Find a skill by ID and resume ID (for security/ownership check)

    Optional<Skill> findByIdAndResumeId(Long id, Long resumeId);


     /** Find skill by name within a resume
     * (Useful due to the unique constraint on (resume_id, name))
     */
    Optional<Skill> findByResumeIdAndNameIgnoreCase(Long resumeId, String name);

    //Check if a skill with the same name already exists for the resume

    boolean existsByResumeIdAndNameIgnoreCase(Long resumeId, String name);

    //Optional: Delete all skills for a resume

    void deleteByResumeId(Long resumeId);
}
