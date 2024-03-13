package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByUser_UserID(int userID);
}
