package com.fdb.backend.Controllers;
import com.fdb.backend.Entities.Option;
import com.fdb.backend.Entities.Question;
import com.fdb.backend.Services.QuestionService;
import com.fdb.backend.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    private QuizService quizService;

    @PostMapping("/save")
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }


    @PutMapping("/{questionId}/assignToQuiz/{quizId}")
    public ResponseEntity<String> assignQuestionToQuiz(@PathVariable int questionId, @PathVariable int quizId) {
        if (questionService.existsById(questionId) && quizService.existsById(quizId)) {
            Question question = questionService.getById(questionId);
            question.setQuiz(quizService.getById(quizId));
            questionService.saveQuestion(question);
            return ResponseEntity.ok("Question assigned to quiz successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question or Quiz not found");
        }
    }

    @GetMapping("/{questionId}/options")
    public ResponseEntity<List<Option>> getAllOptionsByQuestionId(@PathVariable int questionId) {
        List<Option> options = questionService.getAllOptionsByQuestionId(questionId);
        if (options != null) {
            return ResponseEntity.ok(options);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}