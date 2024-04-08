package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailID(String emailID);

    User findByEmailIDAndPassword(String emailID, String password);

    User findByUserID(int userId);

    List<User> findByQuizAttempt_Quiz_QuizId(int quizId);

}
