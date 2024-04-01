package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Quiz;
import com.fdb.backend.Entities.QuizFeedback;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Services.QuizFeedbackService;
import com.fdb.backend.Services.QuizService;
import com.fdb.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/quizFeedbacks")
public class QuizFeedbackController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizFeedbackService quizFeedbackService;

    @PostMapping("/save/{userId}/quiz/{quizId}")
    public ResponseEntity<QuizFeedback> saveFeedback(
            @PathVariable int userId,
            @PathVariable int quizId,
            @RequestBody QuizFeedback feedback) {
        User user = userService.fetchUserByUserId(userId);
        Quiz quiz = quizService.getQuizById(quizId);

        if (user == null || quiz == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        feedback.setUser(user);
        feedback.setQuiz(quiz);
        feedback.setDate(LocalDate.now());
        feedback.setTime(LocalTime.now());

        QuizFeedback savedFeedback = quizFeedbackService.saveFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedback);
    }

}
