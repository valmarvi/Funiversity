package com.switchfully.service;

import com.switchfully.domain.models.Course;
import com.switchfully.domain.models.Professor;
import com.switchfully.domain.repositories.CourseRepository;
import com.switchfully.service.dto.*;
import com.switchfully.service.mappers.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDTO addCourse(CourseDTO courseToAdd) {
        Course course = courseMapper.toDomain(courseToAdd);
        courseRepository.addCourse(course);
        return courseMapper.toDTO(course);
    }

    public List<CourseDTO> getAllCourses(Optional<Integer> studyPoints) {
        return courseMapper.toDTO(courseRepository.getAllCourses(studyPoints));
    }

    public List<CourseDTO> getCoursesByProfessor(String professorId) {
        return courseMapper.toDTO(courseRepository.getCoursesByProfessor(professorId));
    }
}
