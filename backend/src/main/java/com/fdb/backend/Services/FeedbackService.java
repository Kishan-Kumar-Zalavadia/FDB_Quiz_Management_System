package com.fdb.backend.Services;

import com.fdb.backend.Entities.Feedback;
import com.fdb.backend.Repositories.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo repo;

    // ---------------------------------------------------------------------------------------------------
    public Optional<Feedback> fetchFeedbackById(Integer feedbackID) {
        return repo.findById(feedbackID);
    }

    // ---------------------------------------------------------------------------------------------------
    public Feedback saveFeedback(Feedback feedback) {
        return repo.save(feedback);
    }

    public List<Feedback> fetchAllFeedbacks() {
        return repo.findAll();
    }
}