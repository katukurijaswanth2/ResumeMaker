package com.jashwanth.controller;

import com.jashwanth.entity.Resume;
import com.jashwanth.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    // POST /api/users/{userId}/resumes
    // Expects a JSON body with fullName, email, phone, address, linkedInUrl, githubUrl, summary
    @PostMapping("/api/users/{userId}/resumes")
    public ResponseEntity<Resume> createResume(@PathVariable Long userId, @RequestBody Resume resume) {
        Resume saved = resumeService.createResume(
                userId,
                resume.getFullName(),
                resume.getEmail(),
                resume.getPhone(),
                resume.getAddress(),
                resume.getLinkedInUrl(),
                resume.getGithubUrl(),
                resume.getSummary()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/resumes/{id}
    @GetMapping("/api/resumes/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.getResumeById(id));
    }

    // GET /api/users/{userId}/resumes/{id}
    @GetMapping("/api/users/{userId}/resumes/{id}")
    public ResponseEntity<Resume> getResumeByIdForUser(@PathVariable Long userId, @PathVariable Long id) {
        return ResponseEntity.ok(resumeService.getResumeByIdForUser(id, userId));
    }

    // GET /api/users/{userId}/resumes
    @GetMapping("/api/users/{userId}/resumes")
    public ResponseEntity<List<Resume>> getResumesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(resumeService.getResumesByUserId(userId));
    }

    // PUT /api/resumes/{id}
    // Expects a JSON body with the fields to update (nulls are left unchanged)
    @PutMapping("/api/resumes/{id}")
    public ResponseEntity<Resume> updateResume(@PathVariable Long id, @RequestBody Resume resume) {
        Resume updated = resumeService.updateResume(
                id,
                resume.getFullName(),
                resume.getEmail(),
                resume.getPhone(),
                resume.getAddress(),
                resume.getLinkedInUrl(),
                resume.getGithubUrl(),
                resume.getSummary()
        );
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/resumes/{id}
    @DeleteMapping("/api/resumes/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/users/{userId}/resumes/{id}
    @DeleteMapping("/api/users/{userId}/resumes/{id}")
    public ResponseEntity<Void> deleteResumeForUser(@PathVariable Long userId, @PathVariable Long id) {
        resumeService.deleteResumeForUser(id, userId);
        return ResponseEntity.noContent().build();
    }
}