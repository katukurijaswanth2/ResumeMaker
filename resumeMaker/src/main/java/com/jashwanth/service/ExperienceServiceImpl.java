package com.jashwanth.service;

import com.jashwanth.entity.Experience;
import com.jashwanth.entity.Resume;
import com.jashwanth.Custom_exceptions.ResourceNotFoundException;
import com.jashwanth.repository.ExperienceRepository;
import com.jashwanth.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ResumeRepository resumeRepository;

    // Constructor injection (no @Autowired needed on a single constructor in Spring)
    public ExperienceServiceImpl(ExperienceRepository experienceRepository,
                                 ResumeRepository resumeRepository) {
        this.experienceRepository = experienceRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    @Transactional
    public Experience addExperience(Long resumeId, Experience experience) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume", "id", resumeId));

        experience.setId(null); // ensure this is treated as a new entity
        experience.setResume(resume);
        return experienceRepository.save(experience);
    }

    @Override
    public Experience getExperienceByIdAndResumeId(Long experienceId, Long resumeId) {
        return experienceRepository.findByIdAndResumeId(experienceId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experience", "id", experienceId));
    }

    @Override
    public List<Experience> getAllExperienceByResumeId(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new ResourceNotFoundException("Resume", "id", resumeId);
        }
        return experienceRepository.findByResumeIdOrderByStartDateDesc(resumeId);
    }

    @Override
    public List<Experience> getCurrentExperiencesByResumeId(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new ResourceNotFoundException("Resume", "id", resumeId);
        }
        return experienceRepository.findByResumeIdAndCurrentlyWorkingTrue(resumeId);
    }

    @Override
    @Transactional
    public Experience updateExperience(Long experienceId, Long resumeId, Experience updatedExperience) {
        Experience existing = experienceRepository.findByIdAndResumeId(experienceId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experience", "id", experienceId));

        existing.setCompany(updatedExperience.getCompany());
        existing.setRole(updatedExperience.getRole());
        existing.setLocation(updatedExperience.getLocation());
        existing.setStartDate(updatedExperience.getStartDate());
        existing.setEndDate(updatedExperience.getEndDate());
        existing.setCurrentlyWorking(updatedExperience.getCurrentlyWorking());
        existing.setDescription(updatedExperience.getDescription());

        // JPA dirty checking will flush changes at transaction commit,
        // but calling save() explicitly keeps intent clear.
        return experienceRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteExperience(Long experienceId, Long resumeId) {
        Experience existing = experienceRepository.findByIdAndResumeId(experienceId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experience", "id", experienceId));
        experienceRepository.delete(existing);
    }

    @Override
    @Transactional
    public void deleteAllExperienceByResumeId(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new ResourceNotFoundException("Resume", "id", resumeId);
        }
        experienceRepository.deleteByResumeId(resumeId);
    }
}