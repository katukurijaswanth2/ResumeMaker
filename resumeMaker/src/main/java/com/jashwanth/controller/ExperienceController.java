package com.jashwanth.controller;

import com.jashwanth.entity.Experience;
import com.jashwanth.service.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/experience")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    // POST /api/resumes/{resumeId}/experience
    @PostMapping
    public ResponseEntity<Experience> addExperience(@PathVariable Long resumeId,
                                                    @RequestBody Experience experience) {
        Experience saved = experienceService.addExperience(resumeId, experience);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/resumes/{resumeId}/experience
    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperience(@PathVariable Long resumeId) {
        return ResponseEntity.ok(experienceService.getAllExperienceByResumeId(resumeId));
    }

    // GET /api/resumes/{resumeId}/experience/current
    @GetMapping("/current")
    public ResponseEntity<List<Experience>> getCurrentExperience(@PathVariable Long resumeId) {
        return ResponseEntity.ok(experienceService.getCurrentExperiencesByResumeId(resumeId));
    }

    // GET /api/resumes/{resumeId}/experience/{experienceId}
    @GetMapping("/{experienceId}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long resumeId,
                                                        @PathVariable Long experienceId) {
        return ResponseEntity.ok(experienceService.getExperienceByIdAndResumeId(experienceId, resumeId));
    }

    // PUT /api/resumes/{resumeId}/experience/{experienceId}
    @PutMapping("/{experienceId}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long resumeId,
                                                       @PathVariable Long experienceId,
                                                       @RequestBody Experience updatedExperience) {
        Experience updated = experienceService.updateExperience(experienceId, resumeId, updatedExperience);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/resumes/{resumeId}/experience/{experienceId}
    @DeleteMapping("/{experienceId}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long resumeId,
                                                 @PathVariable Long experienceId) {
        experienceService.deleteExperience(experienceId, resumeId);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/resumes/{resumeId}/experience
    @DeleteMapping
    public ResponseEntity<Void> deleteAllExperience(@PathVariable Long resumeId) {
        experienceService.deleteAllExperienceByResumeId(resumeId);
        return ResponseEntity.noContent().build();
    }
}