package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Integer> {
    @Query("SELECT MAX(qa.attemptNumber) FROM QuizAttempt qa WHERE qa.user.userID = :userId AND qa.quiz.quizId = :quizId")
    Optional<Integer> findHighestAttemptNumber(@Param("userId") int userId, @Param("quizId") int quizId);
    List<QuizAttempt> findByQuiz_QuizId(int quizId);
}