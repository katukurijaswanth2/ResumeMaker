package com.jashwanth.service;

import com.jashwanth.Custom_exceptions.ResumeNotFoundException;
import com.jashwanth.Custom_exceptions.UserNotFoundException;
import com.jashwanth.entity.*;
import com.jashwanth.repository.ResumeRepository;
import com.jashwanth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResumeServiceImpl implements  ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository, UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Resume createResume(Long userId, String fullName, String email, String phone,
                               String address, String linkedInUrl, String githubUrl, String summary) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (resumeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("A resume with this email already exists: " + email);
        }

        Resume resume = new Resume(fullName, email, phone, summary, user);
        resume.setAddress(address);
        resume.setLinkedInUrl(linkedInUrl);
        resume.setGithubUrl(githubUrl);

        return resumeRepository.save(resume);
    }

    @Override
    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found with id: " + id));
    }

    @Override
    public Resume getResumeByIdForUser(Long id, Long userId) {
        return resumeRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResumeNotFoundException(
                        "Resume not found with id: " + id + " for this user"));
    }

    @Override
    public List<Resume> getResumesByUserId(Long userId) {
        return resumeRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Resume updateResume(Long id, String fullName, String email, String phone,
                               String address, String linkedInUrl, String githubUrl, String summary) {

        Resume resume = getResumeById(id);

        if (email != null && !email.equals(resume.getEmail()) && resumeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("A resume with this email already exists: " + email);
        }

        if (fullName != null) resume.setFullName(fullName);
        if (email != null) resume.setEmail(email);
        if (phone != null) resume.setPhone(phone);
        if (address != null) resume.setAddress(address);
        if (linkedInUrl != null) resume.setLinkedInUrl(linkedInUrl);
        if (githubUrl != null) resume.setGithubUrl(githubUrl);
        if (summary != null) resume.setSummary(summary);

        return resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public void deleteResume(Long id) {
        if (!resumeRepository.existsById(id)) {
            throw new ResumeNotFoundException("Resume not found with id: " + id);
        }
        resumeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteResumeForUser(Long id, Long userId) {
        Resume resume = getResumeByIdForUser(id, userId);
        resumeRepository.delete(resume);
    }

    @Override
    @Transactional
    public Resume addEducation(Long resumeId, Education education) {
        Resume resume = getResumeById(resumeId);
        resume.addEducation(education);
        return resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public Resume addExperience(Long resumeId, Experience experience) {
        Resume resume = getResumeById(resumeId);
        resume.addExperience(experience);
        return resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public Resume addSkill(Long resumeId, Skill skill) {
        Resume resume = getResumeById(resumeId);
        resume.addSkill(skill);
        return resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public Resume removeEducation(Long resumeId, Long educationId) {
        Resume resume = getResumeById(resumeId);
        Education toRemove = resume.getEducation().stream()
                .filter(e -> e.getId().equals(educationId))
                .findFirst()
                .orElseThrow(() -> new ResumeNotFoundException(
                        "Education entry not found with id: " + educationId));
        resume.removeEducation(toRemove);
        return resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public Resume removeExperience(Long resumeId, Long experienceId) {
        Resume resume = getResumeById(resumeId);
        Experience toRemove = resume.getExperience().stream()
                .filter(e -> e.getId().equals(experienceId))
                .findFirst()
                .orElseThrow(() -> new ResumeNotFoundException(
                        "Experience entry not found with id: " + experienceId));
        resume.removeExperience(toRemove);
        return resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public Resume removeSkill(Long resumeId, Long skillId) {
        Resume resume = getResumeById(resumeId);
        Skill toRemove = resume.getSkills().stream()
                .filter(s -> s.getId().equals(skillId))
                .findFirst()
                .orElseThrow(() -> new ResumeNotFoundException(
                        "Skill entry not found with id: " + skillId));
        resume.removeSkill(toRemove);
        return resumeRepository.save(resume);
    }
}
