package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.QuizFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizFeedbackRepository extends JpaRepository<QuizFeedback, Integer> {
    List<QuizFeedback> findByQuiz_QuizId(int quizId);
}