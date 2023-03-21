package com.switchfully.service.dto;

import com.switchfully.domain.models.Professor;

import java.util.UUID;

public class CourseDTO {
    private final String id;
    private final String name;
    private final int studyPoints;
    private Professor professor;

    public CourseDTO(String name, int studyPoints, Professor professor) {
        this.studyPoints = studyPoints;
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.professor = professor;
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
}
