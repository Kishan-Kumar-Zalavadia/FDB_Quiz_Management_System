package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmailID(String emailID);

    User findByEmailIDAndPassword(String emailID, String password);
}
