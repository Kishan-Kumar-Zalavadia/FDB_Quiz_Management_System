package com.fdb.backend.Services;

import com.fdb.backend.Entities.Question;
import com.fdb.backend.Entities.Quiz;
import com.fdb.backend.Repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(int id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        return optionalQuiz.orElse(null); // Or throw an exception if not found
    }

    public List<Question> getAllQuestionsByQuizId(int quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz != null) {
            return quiz.getQuestions();
        }
        return null;
    }
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public boolean existsById(int id) {
        return quizRepository.existsById(id);
    }

    public Quiz getById(int id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        return optionalQuiz.orElse(null);
    }
}

