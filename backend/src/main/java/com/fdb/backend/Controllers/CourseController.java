package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Course;
import com.fdb.backend.Entities.Department;
import com.fdb.backend.Services.CourseService;
import com.fdb.backend.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/not-enrolled/{userId}")
    public ResponseEntity<List<Course>> getNotEnrolledCourses(@PathVariable int userId) throws Exception {
        List<Course> notEnrolledCourses = courseService.getNotEnrolledCoursesByUserId(userId);
        return new ResponseEntity<>(notEnrolledCourses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public List<Course> getAllCoursesByUserId(@PathVariable int userId) {
        return courseService.getAllCoursesByUserId(userId);
    }
    @PostMapping("/{courseId}/assign-departments")
    public ResponseEntity<String> assignDepartmentsToCourse(@PathVariable int courseId, @RequestBody Integer[] departmentIds) {
        try {
            // Convert the array to an ArrayList
            List<Integer> departmentIdList = Arrays.asList(departmentIds);

            courseService.assignDepartmentsToCourse(courseId, departmentIdList);

            return ResponseEntity.ok("Departments assigned to course successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/professor/{userId}")
    public ResponseEntity<List<Course>> getAllCoursesByProfessorId(@PathVariable int userId) {
        List<Course> courses = courseService.getAllCoursesByProfessorId(userId);
        if (!courses.isEmpty()) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}