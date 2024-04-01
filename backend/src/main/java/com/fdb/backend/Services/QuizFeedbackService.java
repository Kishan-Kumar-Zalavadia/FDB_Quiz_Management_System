package com.fdb.backend.Services;

import com.fdb.backend.Entities.QuizFeedback;
import com.fdb.backend.Repositories.QuizFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizFeedbackService {

    private final QuizFeedbackRepository quizFeedbackRepository;

    @Autowired
    public QuizFeedbackService(QuizFeedbackRepository quizFeedbackRepository) {
        this.quizFeedbackRepository = quizFeedbackRepository;
    }

    public QuizFeedback saveFeedback(QuizFeedback feedback) {
       return quizFeedbackRepository.save(feedback);
    }
}
