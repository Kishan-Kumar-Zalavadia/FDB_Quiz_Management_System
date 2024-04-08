package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.QuizAttempt;
import com.fdb.backend.Services.QuizAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
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

//    @PostMapping("/save/user/{userId}/quiz/{quizId}")
//    public ResponseEntity<QuizAttempt> saveQuizAttempt(@PathVariable int userId, @PathVariable int quizId, @RequestBody QuizAttempt quizAttempt, @RequestBody List<Integer> optionIds) {
//        QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(userId, quizId, quizAttempt, optionIds);
//        if (savedAttempt != null) {
//            return new ResponseEntity<>(savedAttempt, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


//    @PostMapping("/save/user/{userId}/quiz/{quizId}")
//    public ResponseEntity<QuizAttempt> saveQuizAttempt(@PathVariable int userId, @PathVariable int quizId, @RequestBody QuizAttempt quizAttempt) {
//        List<Integer> optionIds = new ArrayList<>();
//        optionIds.add(3);
//        optionIds.add(4);
//        QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(userId, quizId, quizAttempt, optionIds);
//        if (savedAttempt != null) {
//            return new ResponseEntity<>(savedAttempt, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @PostMapping("/save/user/{userId}/quiz/{quizId}/attemptNumber/{attemptNumber}/from/{from}/to/{to}/on/{on}")
//    public ResponseEntity<QuizAttempt> saveQuizAttempt(@PathVariable int userId, @PathVariable int quizId, @PathVariable int attemptNumber, @PathVariable Time from, @PathVariable Time to, @PathVariable Date on , @RequestBody List<Integer> optionIds) {
//        QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(userId, quizId, attemptNumber,from,to,on, optionIds);
//        if (savedAttempt != null) {
//            return new ResponseEntity<>(savedAttempt, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//@PostMapping("/save/user/{userId}/quiz/{quizId}/attempt/{attemptNumber}")
//public ResponseEntity<QuizAttempt> saveQuizAttempt(@PathVariable int userId, @PathVariable int quizId, @PathVariable int attemptNumber, @RequestBody Integer[] optionIds) {
//    List<Integer> optionIdList = Arrays.asList(optionIds);
//    QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(userId, quizId, attemptNumber, optionIdList);
//    if (savedAttempt != null) {
//        return new ResponseEntity<>(savedAttempt, HttpStatus.OK);
//    } else {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//}

    @PostMapping("/save/user/{userId}/quiz/{quizId}/attempt/{attemptNumber}/score/{score}")
    public ResponseEntity<QuizAttempt> saveQuizAttempt(
            @PathVariable int userId,
            @PathVariable int quizId,
            @PathVariable int attemptNumber,
            @PathVariable int score,
            @RequestBody Integer[] optionIds) {
        List<Integer> optionIdList = Arrays.asList(optionIds);
        QuizAttempt savedAttempt = quizAttemptService.saveQuizAttempt(userId, quizId, attemptNumber, optionIdList, score);
        System.out.print("Yoyyo:"+savedAttempt);
        if (savedAttempt != null) {
            return new ResponseEntity<>(savedAttempt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/quiz/{quizId}/highestAttemptNumber")
    public ResponseEntity<Integer> getHighestAttemptNumber(
            @PathVariable int userId,
            @PathVariable int quizId) {
        int highestAttemptNumber = quizAttemptService.getHighestAttemptNumber(userId, quizId);
        if (highestAttemptNumber != -1) {
            return ResponseEntity.ok(highestAttemptNumber);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuizAttempt> getQuizAttemptsByQuizId(@PathVariable int quizId) {
        return quizAttemptService.getQuizAttemptsByQuizId(quizId);
    }

}
