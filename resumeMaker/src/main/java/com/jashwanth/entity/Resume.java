package com.jashwanth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "full_name", length = 150, nullable = false)
    private String fullName;

    @Email
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "linkedin_url", length = 200)
    private String linkedInUrl;

    @Column(name = "github_url", length = 200)
    private String githubUrl;

    @Lob
    @Column(name = "summary", length = 2000)
    private String summary;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("startDate DESC")
    private List<Education> education = new ArrayList<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("startDate DESC")
    private List<Experience> experience = new ArrayList<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();

    // ---------- Constructors ----------

    public Resume() {
    }

    public Resume(String fullName, String email, String phone, String summary, User user) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
        this.user = user;
    }

    public Resume(Long id, String fullName, String email, String phone, String address,
                  String linkedInUrl, String githubUrl, String summary,
                  LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.linkedInUrl = linkedInUrl;
        this.githubUrl = githubUrl;
        this.summary = summary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    // ---------- Lifecycle callbacks ----------

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ---------- Getters and Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    // ---------- Helper methods ----------

    public void addEducation(Education edu) {
        education.add(edu);
        edu.setResume(this);
    }

    public void removeEducation(Education edu) {
        education.remove(edu);
        edu.setResume(null);
    }

    public void addExperience(Experience exp) {
        experience.add(exp);
        exp.setResume(this);
    }

    public void removeExperience(Experience exp) {
        experience.remove(exp);
        exp.setResume(null);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setResume(this);
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.setResume(null);
    }

    // ---------- equals / hashCode / toString ----------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return id != null && id.equals(resume.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}