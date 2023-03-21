package com.switchfully.domain.repositories;


import com.switchfully.domain.models.Course;
import com.switchfully.domain.models.Professor;
import com.switchfully.exception.exceptions.AlreadyExistsException;
import com.switchfully.exception.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {

    private final ProfessorRepository professorRepository;
    private final List<Course> coursesDatabase;

    public CourseRepository(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
        coursesDatabase = new ArrayList<>(
                List.of(new Course("Java Fundamentals", 5, professorRepository.professorsDatabase.get(0)),
                        new Course("Java REST", 2, professorRepository.professorsDatabase.get(0)),
                        new Course("Java Advanced", 10, professorRepository.professorsDatabase.get(1)),
                        new Course("Java Enterprise", 20, professorRepository.professorsDatabase.get(2))));
    }

    public List<Course> getAllCourses(Optional<Integer> studyPoints) {
        return coursesDatabase.stream()
                .filter(course -> course.getStudyPoints() > studyPoints.orElse(0))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesByProfessor(String professorId) {
        return coursesDatabase.stream()
                .filter(course -> course.getProfessor().getId().equals(professorId))
                .collect(Collectors.toList());
    }

    public void addCourse(Course courseToAdd) {
        if (coursesDatabase.contains(courseToAdd)) {
            throw new AlreadyExistsException("This Course Already Exists.");
        }
        coursesDatabase.add(courseToAdd);
    }

    public Course getCourseById(String id) {
        return coursesDatabase.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No Course was Found with the Provided ID"));
    }
}
