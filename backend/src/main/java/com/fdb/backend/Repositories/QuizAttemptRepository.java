package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Integer> {
}