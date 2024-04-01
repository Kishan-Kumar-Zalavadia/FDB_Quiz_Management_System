package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.QuizFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizFeedbackRepository extends JpaRepository<QuizFeedback, Integer> {
    // You can add custom query methods here if needed
}