package com.jashwanth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
        name = "skills",
        uniqueConstraints = @UniqueConstraint(columnNames = {"resume_id", "name"})
)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "proficiency_level", length = 20, nullable = false)
    private ProficiencyLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    // ---------- Constructors ----------

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(String name, ProficiencyLevel level) {
        this.name = name;
        this.level = level;
    }

    public Skill(Long id, String name, ProficiencyLevel level, Resume resume) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.resume = resume;
    }

    // ---------- Getters and Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProficiencyLevel getLevel() {
        return level;
    }

    public void setLevel(ProficiencyLevel level) {
        this.level = level;
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
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return id != null && id.equals(skill.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }

    // ---------- Enum (Better to keep in separate file later) ----------
    public enum ProficiencyLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }
}