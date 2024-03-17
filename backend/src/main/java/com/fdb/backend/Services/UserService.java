package com.fdb.backend.Services;

import com.fdb.backend.Entities.Course;
import com.fdb.backend.Entities.QuizAttempt;
import com.fdb.backend.Entities.Role;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Repositories.CourseRepository;
import com.fdb.backend.Repositories.RoleRepository;
import com.fdb.backend.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // ---------------------------------------------------------------------------------------------------
    public User fetchUserByEmailId(String emailID) {
        return userRepository.findByEmailID(emailID);
    }

    // ---------------------------------------------------------------------------------------------------
    public User fetchUserByEmailIdAndPassword(String emailId, String password) {
        return userRepository.findByEmailIDAndPassword(emailId, password);
    }

    // ---------------------------------------------------------------------------------------------------
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    // ---------------------------------------------------------------------------------------------------

    public User fetchUserByUserId(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // ---------------------------------------------------------------------------------------------------
    public int getRoleIdByUserId(int userId) throws Exception {
        User user = userRepository.findByUserID(userId);
        if (user == null) {
            throw new Exception("User with ID " + userId + " not found");
        }

        return user.getRole().getRoleID();
    }

    // ---------------------------------------------------------------------------------------------------
    public User createUserWithRole(User user, int roleID) {
        Role role = roleRepository.findById(roleID).orElse(null);

        user.setRole(role);

        userRepository.save(user);
        return user;
    }

    // ---------------------------------------------------------------------------------------------------
    public List<Course> getAllCoursesByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getCourses();
    }

    @Transactional
    public void assignCourseToUser(int userID, int courseID) {
//        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found with ID: " + userID));
//        Course course = courseRepository.findById(courseID).orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseID));
//        user.getCourses().add(course);
//        userRepository.save(user);

        // Retrieve the user and course entities from the database
        User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Course course = courseRepository.findById(courseID).orElseThrow(() -> new EntityNotFoundException("Course not found"));

        // Check if the user is already enrolled in the course
        if (user.getCourses().contains(course)) {
            // If user is already enrolled, return without saving
            return;
        }

        // Assign the course to the user
        user.getCourses().add(course);
        userRepository.save(user);
    }


    public List<QuizAttempt> getAllQuizAttemptsByUserId(int userId) {
        // Fetch the user by userId from the repository
        User user = userRepository.findById(userId).orElse(null);

        // If the user is found, return the list of quiz attempts
        if (user != null) {
            return user.getQuizAttempt();
        } else {
            return null;
        }
    }

}
