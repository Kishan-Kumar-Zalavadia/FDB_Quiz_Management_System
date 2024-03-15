package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Feedback;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Services.FeedbackService;
import com.fdb.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

//----------------------------------------------------------------------------------------------------------------
// Get all feedbacks
    @GetMapping("")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.fetchAllFeedbacks();
    }

//----------------------------------------------------------------------------------------------------------------
// Save feedback
@PostMapping("/save/user/{userID}")
public Feedback saveFeedback(@PathVariable int userID, @RequestBody Feedback feedback) {
// Fetch the existing user by userID
    User existingUser = userService.fetchUserByUserId(userID);

    // Set the user for the feedback
    feedback.setUser(existingUser);

    // Set the current date and time
    feedback.setFeedbackDate(new Date());
    feedback.setFeedbackTime(new Time(System.currentTimeMillis()));

    // Save the feedback
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