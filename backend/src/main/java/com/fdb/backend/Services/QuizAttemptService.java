package com.fdb.backend.Services;

import com.fdb.backend.Entities.Option;
import com.fdb.backend.Entities.Quiz;
import com.fdb.backend.Entities.QuizAttempt;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Repositories.OptionRepository;
import com.fdb.backend.Repositories.QuizAttemptRepository;
import com.fdb.backend.Repositories.QuizRepository;
import com.fdb.backend.Repositories.UserRepository;
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

//    public QuizAttempt saveQuizAttempt(int userId, int quizId, QuizAttempt quizAttempt) {
//        // Fetch user and quiz from database
//        User user = userRepository.findById(userId).orElse(null);
//        Quiz quiz = quizRepository.findById(quizId).orElse(null);
//
//        // Check if user and quiz exist
//        if (user == null || quiz == null) {
//            return null;
//        }
//
//        // Set user and quiz in the quizAttempt object
//        quizAttempt.setUser(user);
//        quizAttempt.setQuiz(quiz);
//
//        // Save and return quiz attempt
//        return quizAttemptRepository.save(quizAttempt);
//    }

////    public QuizAttempt saveQuizAttempt(int userId, int quizId, QuizAttempt quizAttempt, List<Integer> optionIds) {
////        // Retrieve user from the database based on userId
////        Optional<User> optionalUser = userRepository.findById(userId);
////        if (!optionalUser.isPresent()) {
////            // Handle user not found scenario
////            return null;
////        }
////        User user = optionalUser.get();
////
////        // Retrieve quiz from the database based on quizId
////        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
////        if (!optionalQuiz.isPresent()) {
////            // Handle quiz not found scenario
////            return null;
////        }
////        Quiz quiz = optionalQuiz.get();
////
////        // Set user and quiz in the quizAttempt object
////        quizAttempt.setUser(user);
////        quizAttempt.setQuiz(quiz);
////
////        // Fetch option objects from the database based on optionIds
////        List<Option> selectedOptions = new ArrayList<>();
////        for (Integer optionId : optionIds) {
////            Optional<Option> optionalOption = optionRepository.findById(optionId);
////            if (optionalOption.isPresent()) {
////                selectedOptions.add(optionalOption.get());
////            } else {
////                // Handle option not found scenario
////                return null;
////            }
////        }
//
//        // Set selected options in the quizAttempt object
//        quizAttempt.setSelectedOptions(selectedOptions);
//
//        // Save the modified quizAttempt object
//        return quizAttemptRepository.save(quizAttempt);
//    }

//    public QuizAttempt saveQuizAttempt(int userId, int quizId, int attemptNumber, Time from, Time to, Date on, List<Integer> optionIds) {
//        // Retrieve user from the database based on userId
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (!optionalUser.isPresent()) {
//            // Handle user not found scenario
//            return null;
//        }
//        User user = optionalUser.get();
//
//        // Retrieve quiz from the database based on quizId
//        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
//        if (!optionalQuiz.isPresent()) {
//            // Handle quiz not found scenario
//            return null;
//        }
//        Quiz quiz = optionalQuiz.get();
//
//        QuizAttempt quizAttempt = new QuizAttempt();
//        quizAttempt.setUser(user);
//        quizAttempt.setQuiz(quiz);
//        quizAttempt.setAttemptNumber(attemptNumber);
//        quizAttempt.setStartTime(from);
//        quizAttempt.setEndTime(to);
//        quizAttempt.setAttemptDate(on);
//
//        // Fetch option objects from the database based on optionIds
//        List<Option> selectedOptions = new ArrayList<>();
//        for (Integer optionId : optionIds) {
//            Optional<Option> optionalOption = optionRepository.findById(optionId);
//            if (optionalOption.isPresent()) {
//                selectedOptions.add(optionalOption.get());
//            } else {
//                // Handle option not found scenario
//                return null;
//            }
//        }
//
//        // Set selected options in the quizAttempt object
//        quizAttempt.setSelectedOptions(selectedOptions);
//
//        // Save the modified quizAttempt object
//        return quizAttemptRepository.save(quizAttempt);
//    }

    public QuizAttempt saveQuizAttempt(int userId, int quizId, int attemptNumber, List<Integer> optionIds) {
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

        // Set selected options in the quizAttempt object
        quizAttempt.setSelectedOptions(selectedOptions);
        System.out.println("Yo:"+quizAttempt);

        // Save the modified quizAttempt object
        return quizAttemptRepository.save(quizAttempt);
    }
}
