package com.switchfully.service.mappers;

import com.switchfully.domain.models.Course;
import com.switchfully.service.dto.CourseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    //Professor -> ProfessorDTO
    public CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getName(), course.getStudyPoints(), course.getProfessor());
    }

    //ProfessorDTO -> Professor
    public Course toDomain(CourseDTO courseDTO) {
        return new Course(courseDTO.getName(), courseDTO.getStudyPoints(), courseDTO.getProfessor());
    }

    //List<Professor> -> List<ProfessorDTO>
    public List<CourseDTO> toDTO(List<Course> coursesList) {
        return coursesList.stream()
                .map(this::toDTO)
                .toList();
    }
}
