package com.fdb.backend.Services;

import com.fdb.backend.Entities.*;
import com.fdb.backend.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private ResultRepository resultRepository;


    public QuizAttempt saveQuizAttempt(int userId, int quizId, int attemptNumber, List<Integer> optionIds, int score) {
        // Retrieve user from the database based on userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            // Handle user not found scenario
            return null;
        }
        User user = optionalUser.get();

        // Retrieve quiz from the database based on quizId
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (!optionalQuiz.isPresent()) {
            // Handle quiz not found scenario
            return null;
        }
        Quiz quiz = optionalQuiz.get();

        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        // Save the score
        Result result = new Result();
        result.setResultScore(score);
        result = resultRepository.save(result); // Save the result and get the managed entity

        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt.setUser(user);
        quizAttempt.setQuiz(quiz);
        quizAttempt.setAttemptNumber(attemptNumber);
        quizAttempt.setEndTime(currentTime);
        quizAttempt.setAttemptDate(currentDate);

        // Fetch option objects from the database based on optionIds
        List<Option> selectedOptions = new ArrayList<>();
        for (Integer optionId : optionIds) {
            Optional<Option> optionalOption = optionRepository.findById(optionId);
            if (optionalOption.isPresent()) {
                selectedOptions.add(optionalOption.get());
            } else {
                // Handle option not found scenario
                return null;
            }
        }

        quizAttempt.setResult(result); // Associate the result with the quizAttempt

        // Set selected options in the quizAttempt object
        quizAttempt.setSelectedOptions(selectedOptions);

        // Save the modified quizAttempt object
        return quizAttemptRepository.save(quizAttempt);
    }


    public int getHighestAttemptNumber(int userId, int quizId) {
        Optional<Integer> highestAttemptNumber = quizAttemptRepository.findHighestAttemptNumber(userId, quizId);
        return highestAttemptNumber.orElse(-1);
    }

    public List<QuizAttempt> getQuizAttemptsByQuizId(int quizId) {
        return quizAttemptRepository.findByQuiz_QuizId(quizId);
    }
}
