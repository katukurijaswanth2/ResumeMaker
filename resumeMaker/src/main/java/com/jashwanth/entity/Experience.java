package com.jashwanth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "company", length = 150, nullable = false)
    private String company;

    @NotBlank
    @Column(name = "role", length = 100, nullable = false)
    private String role;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "currently_working")
    private Boolean currentlyWorking;

    @Lob
    @Column(name = "description", length = 3000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    // ---------- Constructors ----------

    public Experience() {
    }

    public Experience(String company, String role, LocalDate startDate,
                      LocalDate endDate, String description) {
        this.company = company;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Experience(Long id, String company, String role, String location,
                      LocalDate startDate, LocalDate endDate, Boolean currentlyWorking,
                      String description, Resume resume) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentlyWorking = currentlyWorking;
        this.description = description;
        this.resume = resume;
    }

    // ---------- Getters and Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getCurrentlyWorking() {
        return currentlyWorking;
    }

    public void setCurrentlyWorking(Boolean currentlyWorking) {
        this.currentlyWorking = currentlyWorking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    // ---------- equals / hashCode ----------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;
        Experience experience = (Experience) o;
        return id != null && id.equals(experience.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}