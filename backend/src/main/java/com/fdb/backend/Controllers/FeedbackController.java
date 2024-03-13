package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Feedback;
import com.fdb.backend.Services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

//----------------------------------------------------------------------------------------------------------------
// Get all feedbacks
    @GetMapping("")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.fetchAllFeedbacks();
    }

//----------------------------------------------------------------------------------------------------------------
// Save feedback
    @PostMapping("/save")
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.saveFeedback(feedback);
        return savedFeedback;
    }

//----------------------------------------------------------------------------------------------------------------
// Get feedback by ID
    @GetMapping("/{feedbackID}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int feedbackID) {
        Optional<Feedback> feedback = feedbackService.fetchFeedbackById(feedbackID);
        return feedback.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//----------------------------------------------------------------------------------------------------------------
// Get feedbacks by userID
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserID(@PathVariable int userID) {
        List<Feedback> feedbacks = feedbackService.fetchFeedbacksByUserID(userID);
        return ResponseEntity.ok(feedbacks);
    }
}