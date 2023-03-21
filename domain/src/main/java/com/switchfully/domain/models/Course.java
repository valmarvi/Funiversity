package com.switchfully.domain.models;

import com.switchfully.domain.repositories.ProfessorRepository;

import java.util.UUID;

public class Course {
    private final String id;
    private String name;
    private int studyPoints;
    private Professor professor;

    public Course(String name, int studyPoints, Professor professor) {
        this.studyPoints = studyPoints;
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return studyPoints;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudyPoints(int studyPoints) {
        this.studyPoints = studyPoints;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
