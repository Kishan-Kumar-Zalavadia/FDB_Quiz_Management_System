package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.User;
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

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

// ---------------------------------------------------------------------------------------------------
// Save User
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailID();
        if (tempEmailId != null && !tempEmailId.isEmpty()) {
            User existingUser = service.fetchUserByEmailId(tempEmailId);
            if (existingUser != null) {
                throw new Exception("User with " + tempEmailId + " already exists");
            }
        }
        return service.saveUser(user);
    }
// ---------------------------------------------------------------------------------------------------
// User Login

//    @PostMapping("/login")
//    public User loginUser(@RequestBody User user) throws Exception {
//        String tempEmailId = user.getEmailID();
//        String tempPass = user.getPassword();
//        User userObj = null;
//        if (tempEmailId != null && tempPass != null) {
//            userObj = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
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
            userObj = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
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
        return service.fetchAllUsers();
    }

// ---------------------------------------------------------------------------------------------------
// Get user by userID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) throws Exception{
        User user = service.fetchUserByUserId(userId);
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
            int roleId = service.getRoleIdByUserId(userId);
            System.out.println("RoleID: "+roleId);
            return ResponseEntity.ok(roleId);
        } catch (Exception e) {
            System.out.println("Error getting roleID by userID");
//            return ResponseEntity.notFound().build();
            return ResponseEntity.ok(0);
        }

    }

}
