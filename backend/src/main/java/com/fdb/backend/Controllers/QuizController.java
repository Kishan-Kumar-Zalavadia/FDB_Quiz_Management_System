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
@CrossOrigin(origins = "*")
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


//    @GetMapping("/{quizId}/questions")
//    public List<Question> getAllQuestionsByQuizId(@PathVariable int quizId) {
//        return getAllQuestionsByQuizId(quizId);
//    }


    @PostMapping("/save")
    public ResponseEntity<Quiz> saveQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }


    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<Question>> getAllQuestionsByQuizId(@PathVariable int quizId) {
        List<Question> questions = quizService.getAllQuestionsByQuizId(quizId);
        if (questions != null) {
            return ResponseEntity.ok(questions);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to assign course to quiz
    @PostMapping("/{quizId}/assign-course/{courseId}")
    public ResponseEntity<String> assignCourseToQuiz(@PathVariable int quizId, @PathVariable int courseId) {
        quizService.assignCourseToQuiz(quizId, courseId);
        return new ResponseEntity<>("Course assigned to quiz successfully", HttpStatus.OK);
    }

    // Endpoint to get all quizzes by course ID
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Quiz>> getAllQuizzesByCourseId(@PathVariable int courseId) {
        List<Quiz> quizzes = quizService.getAllQuizzesByCourseId(courseId);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public List<Quiz> getAllQuizzesForUser(@PathVariable int userId) {
        return quizService.getAllQuizzesForUser(userId);
    }

}