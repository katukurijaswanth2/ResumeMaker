package com.jashwanth.service;

import com.jashwanth.entity.Education;
import com.jashwanth.entity.Experience;
import com.jashwanth.entity.Resume;
import com.jashwanth.entity.Skill;

import java.util.List;

public interface ResumeService {
    Resume createResume(Long userId, String fullName, String email, String phone,
                        String address, String linkedInUrl, String githubUrl, String summary);

    Resume getResumeById(Long id);

    Resume getResumeByIdForUser(Long id, Long userId);

    List<Resume> getResumesByUserId(Long userId);

    Resume updateResume(Long id, String fullName, String email, String phone,
                        String address, String linkedInUrl, String githubUrl, String summary);

    void deleteResume(Long id);

    void deleteResumeForUser(Long id, Long userId);

    Resume addEducation(Long resumeId, Education education);

    Resume addExperience(Long resumeId, Experience experience);

    Resume addSkill(Long resumeId, Skill skill);

    Resume removeEducation(Long resumeId, Long educationId);

    Resume removeExperience(Long resumeId, Long experienceId);

    Resume removeSkill(Long resumeId, Long skillId);
}
