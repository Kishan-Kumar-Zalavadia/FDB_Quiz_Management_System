package com.fdb.backend.Services;

import com.fdb.backend.Entities.User;
import com.fdb.backend.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    public User saveUser(User user) {
        return repo.save(user);
    }

    public User fetchUserByEmailId(String emailID) {
        return repo.findByEmailID(emailID);
    }

    public User fetchUserByEmailIdAndPassword(String emailId, String password) {
        return repo.findByEmailIDAndPassword(emailId, password);
    }

    public List<User> fetchAllUsers() {
        return repo.findAll();
    }

    public User fetchUserByUserId(int userId) {
        return repo.findById(userId).orElse(null);
    }

}
