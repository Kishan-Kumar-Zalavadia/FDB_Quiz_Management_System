package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Course;
import com.fdb.backend.Entities.Profile;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Services.ProfileService;
import com.fdb.backend.Services.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @Autowired
    private ProfileService profileService;

// ---------------------------------------------------------------------------------------------------
// Save User
@PostMapping("/register")
public User registerUser(@RequestBody User user) throws Exception {
    String tempEmailId = user.getEmailID();
    if (tempEmailId != null && !tempEmailId.isEmpty()) {
        User existingUser = userService.fetchUserByEmailId(tempEmailId);
        if (existingUser != null) {
            throw new Exception("User with " + tempEmailId + " already exists");
        }
    }
    return userService.saveUser(user);
}
// ---------------------------------------------------------------------------------------------------
// User Login

//    @PostMapping("/login")
//    public User loginUser(@RequestBody User user) throws Exception {
//        String tempEmailId = user.getEmailID();
//        String tempPass = user.getPassword();
//        User userObj = null;
//        if (tempEmailId != null && tempPass != null) {
//            userObj = userService.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
//        }
//        if (userObj == null) {
//            throw new Exception("Bad Credentials");
//        }
//        return userObj;
//    }


// ---------------------------------------------------------------------------------------------------
// User login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String tempEmailId = user.getEmailID();
        String tempPass = user.getPassword();
        User userObj = null;
        if (tempEmailId != null && tempPass != null) {
            userObj = userService.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
//            if (userObj != null && userObj.getProfile() == null) {
//                // Create an empty profile for the user
//                Profile emptyProfile = new Profile();
//                // Save the empty profile to the user object
//                userObj.setProfile(emptyProfile);
//                // Update the user object in the database
//                userService.saveUser(userObj);
//            }
        }
        if (userObj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
        }
        return ResponseEntity.ok(userObj);
    }


// ---------------------------------------------------------------------------------------------------
// Get all Users
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }

// ---------------------------------------------------------------------------------------------------
// Get user by userID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) throws Exception{
        User user = userService.fetchUserByUserId(userId);
        if (user == null) {
            // Handle the case where the user with the specified ID is not found.
            throw new Exception("User with ID " + userId + " not found");
        }
        return user;
    }

// ---------------------------------------------------------------------------------------------------
// Get roleID by userID
    @GetMapping("/{userId}/role")
    public ResponseEntity<Integer> getRoleByUserId(@PathVariable int userId) {
        try {
            int roleId = userService.getRoleIdByUserId(userId);
            System.out.println("RoleID: "+roleId);
            return ResponseEntity.ok(roleId);
        } catch (Exception e) {
            System.out.println("Error getting roleID by userID");
//            return ResponseEntity.notFound().build();
            return ResponseEntity.ok(0);
        }

    }
// ---------------------------------------------------------------------------------------------------
    // Create user with role
//    @PostMapping("/register/role/{roleID")
//    public User createUserWithRole(@RequestBody User user, @RequestParam int roleID) {
//        String tempEmailId = user.getEmailID();
//        if (tempEmailId != null && !tempEmailId.isEmpty()) {
//            User existingUser = userService.fetchUserByEmailId(tempEmailId);
//            if (existingUser != null) {
//                throw new Exception("User with " + tempEmailId + " already exists");
//            }
//        }
//        return userService.createUserWithRole(user, roleID);
//    }

    @PostMapping("/register/withRoleID/{roleID}")
    public ResponseEntity<User> createUserWithRole(@RequestBody User user, @PathVariable int roleID) {
        try {
            // Check if a user with the same email already exists
            String tempEmailId = user.getEmailID();
            if (tempEmailId != null && !tempEmailId.isEmpty()) {
                User existingUser = userService.fetchUserByEmailId(tempEmailId);
                if (existingUser != null) {
                    throw new Exception("User with email " + tempEmailId + " already exists");
                }
            }

            // If no user with the same email exists, proceed with creating the user
            User createdUser = userService.createUserWithRole(user, roleID);
            if (createdUser != null && createdUser.getProfile() == null) {
                // Create an empty profile for the user
                Profile emptyProfile = new Profile();
                // Save the empty profile to the user object
                createdUser.setProfile(emptyProfile);
                // Update the user object in the database
                userService.saveUser(createdUser);
            }
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle exception
        }
    }



    @PostMapping("/register/withRoleID/{roleID}/department/{departmentID}")
    public ResponseEntity<User> createUserWithRoleAndDepartment(@RequestBody User user, @PathVariable int roleID, @PathVariable int departmentID) {
        try {
            // Check if a user with the same email already exists
            String tempEmailId = user.getEmailID();
            if (tempEmailId != null && !tempEmailId.isEmpty()) {
                User existingUser = userService.fetchUserByEmailId(tempEmailId);
                if (existingUser != null) {
                    throw new Exception("User with email " + tempEmailId + " already exists");
                }
            }

            // If no user with the same email exists, proceed with creating the user
            User createdUser = userService.createUserWithRole(user, roleID);
            if (createdUser != null && createdUser.getProfile() == null) {
                // Create an empty profile for the user
                Profile emptyProfile = new Profile();

                // Save the empty profile to the user object
                createdUser.setProfile(emptyProfile);
                // Update the user object in the database
                userService.saveUser(createdUser);

            }
//            System.out.println("UserID:"+createdUser.getUserID());
//            System.out.println("ProfileID:"+createdUser.getProfile().getProfileID());
            this.profileService.assignDepartment(createdUser.getProfile().getProfileID(),departmentID);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle exception
        }
    }



    // ---------------------------------------------------------------------------------------------------
    @GetMapping("/{userId}/courses")
    public List<Course> getAllCoursesByUserId(@PathVariable int userId) {
        return userService.getAllCoursesByUserId(userId);
    }

    // ---------------------------------------------------------------------------------------------------
//    @PostMapping("/{userID}/enroll/{courseID}")
//    public ResponseEntity<String> assignCourseToUser(@PathVariable int userID, @PathVariable int courseID, @RequestBody int dummy) {
//        userService.assignCourseToUser(userID, courseID);
//        return ResponseEntity.status(HttpStatus.OK).body("Course with ID " + courseID + " assigned to user with ID " + userID);
//    }


    // ---------------------------------------------------------------------------------------------------
    @PostMapping("/{userID}/enroll")
    public ResponseEntity<String> assignCourseToUser(@PathVariable int userID, @RequestBody Course course) {
        userService.assignCourseToUser(userID, course.getCourseId());
        return ResponseEntity.status(HttpStatus.OK).body("Course with ID " + course.getCourseId() + " assigned to user with ID " + userID);
    }
}
