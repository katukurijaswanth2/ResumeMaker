package com.jashwanth.controller;

import com.jashwanth.entity.Education;
import com.jashwanth.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    // POST /api/resumes/{resumeId}/education
    @PostMapping
    public ResponseEntity<Education> addEducation(@PathVariable Long resumeId,
                                                  @RequestBody Education education) {
        Education saved = educationService.addEducation(resumeId, education);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/resumes/{resumeId}/education
    @GetMapping
    public ResponseEntity<List<Education>> getAllEducation(@PathVariable Long resumeId) {
        return ResponseEntity.ok(educationService.getAllEducationByResumeId(resumeId));
    }

    // GET /api/resumes/{resumeId}/education/{educationId}
    @GetMapping("/{educationId}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long resumeId,
                                                      @PathVariable Long educationId) {
        return ResponseEntity.ok(educationService.getEducationByIdAndResumeId(educationId, resumeId));
    }

    // PUT /api/resumes/{resumeId}/education/{educationId}
    @PutMapping("/{educationId}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long resumeId,
                                                     @PathVariable Long educationId,
                                                     @RequestBody Education updatedEducation) {
        Education updated = educationService.updateEducation(educationId, resumeId, updatedEducation);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/resumes/{resumeId}/education/{educationId}
    @DeleteMapping("/{educationId}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long resumeId,
                                                @PathVariable Long educationId) {
        educationService.deleteEducation(educationId, resumeId);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/resumes/{resumeId}/education
    @DeleteMapping
    public ResponseEntity<Void> deleteAllEducation(@PathVariable Long resumeId) {
        educationService.deleteAllEducationByResumeId(resumeId);
        return ResponseEntity.noContent().build();
    }
}