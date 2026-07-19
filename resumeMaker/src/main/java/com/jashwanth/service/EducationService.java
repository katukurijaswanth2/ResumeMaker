package com.jashwanth.service;

import com.jashwanth.entity.Education;

import java.util.List;

public interface EducationService {

    /**
     * Create a new education entry under the given resume.
     *
     * @param resumeId  the resume this education belongs to
     * @param education the education data to persist
     * @return the saved Education (with generated ID)
     */
    Education addEducation(Long resumeId, Education education);

    /**
     * Fetch a single education entry, scoped to a resume for security
     * (prevents one user from fetching another user's education by guessing IDs).
     */
    Education getEducationByIdAndResumeId(Long educationId, Long resumeId);

    /**
     * Fetch all education entries for a resume, ordered by start date (newest first).
     */
    List<Education> getAllEducationByResumeId(Long resumeId);

    /**
     * Update an existing education entry, scoped to a resume.
     */
    Education updateEducation(Long educationId, Long resumeId, Education updatedEducation);

    /**
     * Delete a single education entry, scoped to a resume.
     */
    void deleteEducation(Long educationId, Long resumeId);

    /**
     * Delete all education entries belonging to a resume
     * (e.g. when the resume itself is deleted, or bulk-replaced).
     */
    void deleteAllEducationByResumeId(Long resumeId);
}
