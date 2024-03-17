package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.QuizAttempt;
import com.fdb.backend.Services.QuizAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz-attempts")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService quizAttemptService;

//    @PostMapping("/save/user/{userId}/quiz/{quizId}")
//    public ResponseEntity<QuizAttempt> saveQuizAttempt(@PathVariable int userId, @PathVariable int quizId, @RequestBody QuizAttempt quizAttempt) {
//        QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(userId, quizId, quizAttempt);
//        if (savedAttempt != null) {
//            return new ResponseEntity<>(savedAttempt, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/save/user/{userId}/quiz/{quizId}")
    public ResponseEntity<QuizAttempt> saveQuizAttempt(@PathVariable int userId, @PathVariable int quizId, @RequestBody QuizAttempt quizAttempt) {
        List<Integer> optionIds = new ArrayList<>();
        optionIds.add(3);
        optionIds.add(4);
        QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(userId, quizId, quizAttempt, optionIds);
        if (savedAttempt != null) {
            return new ResponseEntity<>(savedAttempt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
