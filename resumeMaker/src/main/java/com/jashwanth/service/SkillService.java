package com.jashwanth.service;

import com.jashwanth.entity.Skill;

import java.util.List;

public interface SkillService {

    /**
     * Add a new skill under the given resume.
     * Throws DuplicateResourceException if a skill with the same name
     * (case-insensitive) already exists for this resume.
     *
     * @param resumeId the resume this skill belongs to
     * @param skill    the skill data to persist
     * @return the saved Skill (with generated ID)
     */
    Skill addSkill(Long resumeId, Skill skill);

    /**
     * Fetch a single skill, scoped to a resume for security
     * (prevents one user from fetching another user's skill by guessing IDs).
     */
    Skill getSkillByIdAndResumeId(Long skillId, Long resumeId);

    /**
     * Fetch all skills for a resume.
     */
    List<Skill> getAllSkillsByResumeId(Long resumeId);

    /**
     * Update an existing skill, scoped to a resume.
     * Throws DuplicateResourceException if renaming would collide with
     * another existing skill on the same resume.
     */
    Skill updateSkill(Long skillId, Long resumeId, Skill updatedSkill);

    /**
     * Delete a single skill, scoped to a resume.
     */
    void deleteSkill(Long skillId, Long resumeId);

    /**
     * Delete all skills belonging to a resume
     * (e.g. when the resume itself is deleted, or bulk-replaced).
     */
    void deleteAllSkillsByResumeId(Long resumeId);
}