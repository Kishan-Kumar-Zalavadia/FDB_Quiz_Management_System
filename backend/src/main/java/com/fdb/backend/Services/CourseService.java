package com.fdb.backend.Services;

import com.fdb.backend.Entities.Course;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Repositories.CourseRepository;
import com.fdb.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    private UserRepository userRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int courseId) {
        return courseRepository.findById(courseId);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCoursesByUserId(int userId) {
        return courseRepository.findByUsersUserID(userId);
    }


    public List<Course> getNotEnrolledCoursesByUserId(int userId) throws Exception {
        // Get the user by ID
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            // Handle case where user is not found
            throw new Exception("User with ID " + userId + " not found");
        }

        // Get the courses
        List<Course> allCourses = courseRepository.findAll();

        // Get the courses enrolled by the user
        List<Course> enrolledCourses = user.getCourses();

        // Filter out the courses not enrolled by the user
        List<Course> notEnrolledCourses = allCourses.stream()
                .filter(course -> !enrolledCourses.contains(course))
                .collect(Collectors.toList());

        return notEnrolledCourses;
    }
}