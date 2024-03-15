package com.fdb.backend.Controllers;
import com.fdb.backend.Entities.Question;
import com.fdb.backend.Entities.Quiz;
import com.fdb.backend.Services.QuestionService;
import com.fdb.backend.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{quizId}")
    public Quiz getQuizById(@PathVariable int quizId) {
        return quizService.getQuizById(quizId);
    }


    @GetMapping("/{quizId}/questions")
    public List<Question> getAllQuestionsByQuizId(@PathVariable int quizId) {
        return getAllQuestionsByQuizId(quizId);
    }


    @PostMapping("/save")
    public ResponseEntity<Quiz> saveQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }



}