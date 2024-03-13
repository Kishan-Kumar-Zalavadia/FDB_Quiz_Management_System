package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
}
