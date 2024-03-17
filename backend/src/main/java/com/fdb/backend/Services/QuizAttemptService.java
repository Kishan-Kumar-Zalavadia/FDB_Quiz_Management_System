package com.fdb.backend.Services;

import com.fdb.backend.Entities.Quiz;
import com.fdb.backend.Entities.QuizAttempt;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Repositories.QuizAttemptRepository;
import com.fdb.backend.Repositories.QuizRepository;
import com.fdb.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    public QuizAttempt saveQuizAttempt(int userId, int quizId, QuizAttempt quizAttempt) {
        // Fetch user and quiz from database
        User user = userRepository.findById(userId).orElse(null);
        Quiz quiz = quizRepository.findById(quizId).orElse(null);

        // Check if user and quiz exist
        if (user == null || quiz == null) {
            return null;
        }

        // Set user and quiz in the quizAttempt object
        quizAttempt.setUser(user);
        quizAttempt.setQuiz(quiz);

        // Save and return quiz attempt
        return quizAttemptRepository.save(quizAttempt);
    }
}
