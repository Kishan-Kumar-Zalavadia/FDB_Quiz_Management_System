package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Question;
import com.fdb.backend.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
    List<Question> findByQuizId(int quizId);
    List<Quiz> findAllByCourse_CourseId(int courseId);

    List<Quiz> findAllByCourse_CourseIdIn(List<Integer> courseIds);

}
