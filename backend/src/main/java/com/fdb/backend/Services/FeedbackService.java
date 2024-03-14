package com.fdb.backend.Services;

import com.fdb.backend.Entities.Feedback;
import com.fdb.backend.Repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // ---------------------------------------------------------------------------------------------------
    public Optional<Feedback> fetchFeedbackById(Integer feedbackID) {
        return feedbackRepository.findById(feedbackID);
    }

    // ---------------------------------------------------------------------------------------------------
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // ---------------------------------------------------------------------------------------------------
    public List<Feedback> fetchAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // ---------------------------------------------------------------------------------------------------
    public List<Feedback> fetchFeedbacksByUserID(int userID) {
        return feedbackRepository.findByUser_UserID(userID);
    }
}