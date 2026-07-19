package com.jashwanth.controller;

import com.jashwanth.entity.Skill;
import com.jashwanth.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // POST /api/resumes/{resumeId}/skills
    @PostMapping
    public ResponseEntity<Skill> addSkill(@PathVariable Long resumeId,
                                          @RequestBody Skill skill) {
        Skill saved = skillService.addSkill(resumeId, skill);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/resumes/{resumeId}/skills
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills(@PathVariable Long resumeId) {
        return ResponseEntity.ok(skillService.getAllSkillsByResumeId(resumeId));
    }

    // GET /api/resumes/{resumeId}/skills/{skillId}
    @GetMapping("/{skillId}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long resumeId,
                                              @PathVariable Long skillId) {
        return ResponseEntity.ok(skillService.getSkillByIdAndResumeId(skillId, resumeId));
    }

    // PUT /api/resumes/{resumeId}/skills/{skillId}
    @PutMapping("/{skillId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long resumeId,
                                             @PathVariable Long skillId,
                                             @RequestBody Skill updatedSkill) {
        Skill updated = skillService.updateSkill(skillId, resumeId, updatedSkill);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/resumes/{resumeId}/skills/{skillId}
    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long resumeId,
                                            @PathVariable Long skillId) {
        skillService.deleteSkill(skillId, resumeId);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/resumes/{resumeId}/skills
    @DeleteMapping
    public ResponseEntity<Void> deleteAllSkills(@PathVariable Long resumeId) {
        skillService.deleteAllSkillsByResumeId(resumeId);
        return ResponseEntity.noContent().build();
    }
}