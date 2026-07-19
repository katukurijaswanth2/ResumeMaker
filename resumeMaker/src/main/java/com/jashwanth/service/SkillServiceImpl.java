package com.jashwanth.service;

import com.jashwanth.entity.Resume;
import com.jashwanth.entity.Skill;
import com.jashwanth.Custom_exceptions.DuplicateResourceException;
import com.jashwanth.Custom_exceptions.ResourceNotFoundException;
import com.jashwanth.repository.ResumeRepository;
import com.jashwanth.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final ResumeRepository resumeRepository;

    // Constructor injection (no @Autowired needed on a single constructor in Spring)
    public SkillServiceImpl(SkillRepository skillRepository,
                            ResumeRepository resumeRepository) {
        this.skillRepository = skillRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    @Transactional
    public Skill addSkill(Long resumeId, Skill skill) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume", "id", resumeId));

        if (skillRepository.existsByResumeIdAndNameIgnoreCase(resumeId, skill.getName())) {
            throw new DuplicateResourceException("Skill", "name", skill.getName());
        }

        skill.setId(null); // ensure this is treated as a new entity
        skill.setResume(resume);
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillByIdAndResumeId(Long skillId, Long resumeId) {
        return skillRepository.findByIdAndResumeId(skillId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));
    }

    @Override
    public List<Skill> getAllSkillsByResumeId(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new ResourceNotFoundException("Resume", "id", resumeId);
        }
        return skillRepository.findByResumeId(resumeId);
    }

    @Override
    @Transactional
    public Skill updateSkill(Long skillId, Long resumeId, Skill updatedSkill) {
        Skill existing = skillRepository.findByIdAndResumeId(skillId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));

        // Only enforce the duplicate-name check if the name is actually changing
        boolean nameChanged = !existing.getName().equalsIgnoreCase(updatedSkill.getName());
        if (nameChanged && skillRepository.existsByResumeIdAndNameIgnoreCase(resumeId, updatedSkill.getName())) {
            throw new DuplicateResourceException("Skill", "name", updatedSkill.getName());
        }

        existing.setName(updatedSkill.getName());
        existing.setLevel(updatedSkill.getLevel());

        // JPA dirty checking will flush changes at transaction commit,
        // but calling save() explicitly keeps intent clear.
        return skillRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteSkill(Long skillId, Long resumeId) {
        Skill existing = skillRepository.findByIdAndResumeId(skillId, resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));
        skillRepository.delete(existing);
    }

    @Override
    @Transactional
    public void deleteAllSkillsByResumeId(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new ResourceNotFoundException("Resume", "id", resumeId);
        }
        skillRepository.deleteByResumeId(resumeId);
    }
}