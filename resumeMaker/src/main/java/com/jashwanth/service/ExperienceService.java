package com.jashwanth.service;

import com.jashwanth.entity.Experience;

import java.util.List;

public interface ExperienceService {

    /**
     * Create a new experience entry under the given resume.
     *
     * @param resumeId   the resume this experience belongs to
     * @param experience the experience data to persist
     * @return the saved Experience (with generated ID)
     */
    Experience addExperience(Long resumeId, Experience experience);

    /**
     * Fetch a single experience entry, scoped to a resume for security
     * (prevents one user from fetching another user's experience by guessing IDs).
     */
    Experience getExperienceByIdAndResumeId(Long experienceId, Long resumeId);

    /**
     * Fetch all experience entries for a resume, ordered by start date (newest first).
     */
    List<Experience> getAllExperienceByResumeId(Long resumeId);

    /**
     * Fetch only the experience entries currently marked as "currently working".
     */
    List<Experience> getCurrentExperiencesByResumeId(Long resumeId);

    /**
     * Update an existing experience entry, scoped to a resume.
     */
    Experience updateExperience(Long experienceId, Long resumeId, Experience updatedExperience);

    /**
     * Delete a single experience entry, scoped to a resume.
     */
    void deleteExperience(Long experienceId, Long resumeId);

    /**
     * Delete all experience entries belonging to a resume
     * (e.g. when the resume itself is deleted, or bulk-replaced).
     */
    void deleteAllExperienceByResumeId(Long resumeId);
}