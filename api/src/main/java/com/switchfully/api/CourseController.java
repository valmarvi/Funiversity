package com.switchfully.api;

import com.switchfully.domain.models.Feature;
import com.switchfully.service.CourseService;
import com.switchfully.service.SecurityService;
import com.switchfully.service.dto.CourseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "courses")
public class CourseController {
    private final CourseService courseService;
    private final SecurityService securityService;
    private final Logger myLogger = LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseService courseService, SecurityService securityService) {
        this.courseService = courseService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED) //http://localhost:8080/courses
    @PostMapping(consumes = "application/json", produces = "application/json")
    public CourseDTO addCourse(@RequestBody CourseDTO courseToAdd, @RequestHeader String authorization) {
        myLogger.info("Adding a New Course to the Database.");
        securityService.validateAuthorization(authorization, Feature.ADD_COURSE);
        return courseService.addCourse(courseToAdd);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json") //http://localhost:8080/courses //http://localhost:8080/courses?studyPoints="studyPoints"
    public List<CourseDTO> getAllCourses(@RequestParam(required = false) Optional<Integer> studyPoints, @RequestHeader String authorization) {
        myLogger.info("Retrieving All the Courses from the Database with More Than " + studyPoints.orElse(0) + " Study Points.");
        securityService.validateAuthorization(authorization, Feature.GET_ALL_COURSES);
        return courseService.getAllCourses(studyPoints);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json", path = "professor") //http://localhost:8080/courses/professor?id="professorId"
    public List<CourseDTO> getCoursesByProfessor(@RequestParam String professorId) {
        return courseService.getCoursesByProfessor(professorId);
    }
}
