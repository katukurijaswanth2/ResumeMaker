package com.jashwanth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "institution", length = 150, nullable = false)
    private String institution;

    @NotBlank
    @Column(name = "degree", length = 100, nullable = false)
    private String degree;

    @Column(name = "field_of_study", length = 100)
    private String fieldOfStudy;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "currently_studying")
    private Boolean currentlyStudying;

    @Column(name = "gpa", precision = 3, scale = 2)
    private Double gpa;

    @Lob
    @Column(name = "description", length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    // ---------- Constructors ----------

    public Education() {
    }

    public Education(String institution, String degree, String fieldOfStudy,
                     LocalDate startDate, LocalDate endDate) {
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Education(Long id, String institution, String degree, String fieldOfStudy,
                     LocalDate startDate, LocalDate endDate, Boolean currentlyStudying,
                     Double gpa, String description, Resume resume) {
        this.id = id;
        this.institution = institution;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentlyStudying = currentlyStudying;
        this.gpa = gpa;
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

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
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

    public Boolean getCurrentlyStudying() {
        return currentlyStudying;
    }

    public void setCurrentlyStudying(Boolean currentlyStudying) {
        this.currentlyStudying = currentlyStudying;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
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
        if (!(o instanceof Education)) return false;
        Education education = (Education) o;
        return id != null && id.equals(education.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", institution='" + institution + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}