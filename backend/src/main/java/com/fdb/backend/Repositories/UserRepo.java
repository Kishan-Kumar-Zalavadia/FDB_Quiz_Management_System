package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    public User findByEmailId(String emailID);

    public User findByEmailIdAndPassword(String emailId, String password);

}