package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}

