package com.jashwanth.service;

import com.jashwanth.entity.Education;
import com.jashwanth.entity.Resume;
import com.jashwanth.Custom_exceptions.ResourceNotFoundException;
import com.jashwanth.repository.EducationRepository;
import com.jashwanth.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ResumeRepository resumeRepository;

    // Constructor injection (no @Autowired needed on a single constructor in Spring)
    public EducationServiceImpl(EducationRepository educationRepository,
                                ResumeRepository resumeRepository) {
        this.educationRepository = educationRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    @Transactional
    public Education addEducation(Long resumeId, Education education) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume", "id", resumeId));

        education.setId(null); // ensure this is treated as a new entity
        education.setResume(resume);
        return educationRepository.save(education);
    }

    @Override
    public Education getEducationByIdAndResumeId(Long educationId, Long resumeId) {
        return educationRepository.findByIdAndResumeId(educationId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Education", "id", educationId));
    }

    @Override
    public List<Education> getAllEducationByResumeId(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new ResourceNotFoundException("Resume", "id", resumeId);
        }
        return educationRepository.findByResumeIdOrderByStartDateDesc(resumeId);
    }

    @Override
    @Transactional
    public Education updateEducation(Long educationId, Long resumeId, Education updatedEducation) {
        Education existing = educationRepository.findByIdAndResumeId(educationId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Education", "id", educationId));

        existing.setInstitution(updatedEducation.getInstitution());
        existing.setDegree(updatedEducation.getDegree());
        existing.setFieldOfStudy(updatedEducation.getFieldOfStudy());
        existing.setStartDate(updatedEducation.getStartDate());
        existing.setEndDate(updatedEducation.getEndDate());
        existing.setCurrentlyStudying(updatedEducation.getCurrentlyStudying());
        existing.setGpa(updatedEducation.getGpa());
        existing.setDescription(updatedEducation.getDescription());

        // JPA dirty checking will flush changes at transaction commit,
        // but calling save() explicitly keeps intent clear.
        return educationRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteEducation(Long educationId, Long resumeId) {
        Education existing = educationRepository.findByIdAndResumeId(educationId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Education", "id", educationId));
        educationRepository.delete(existing);
    }

    @Override
    @Transactional
    public void deleteAllEducationByResumeId(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new ResourceNotFoundException("Resume", "id", resumeId);
        }
        educationRepository.deleteByResumeId(resumeId);
    }
}