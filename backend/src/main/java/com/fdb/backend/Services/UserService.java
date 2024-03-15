package com.fdb.backend.Services;

import com.fdb.backend.Entities.Role;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Repositories.RoleRepository;
import com.fdb.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


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

    public User createUserWithRole(User user, int roleID) {
        Role role = roleRepository.findById(roleID).orElse(null);

        user.setRole(role);

        userRepository.save(user);
        return user;
    }

}
