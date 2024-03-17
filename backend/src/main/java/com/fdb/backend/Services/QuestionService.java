package com.fdb.backend.Services;

import com.fdb.backend.Entities.Media;
import com.fdb.backend.Entities.Option;
import com.fdb.backend.Entities.Question;
import com.fdb.backend.Repositories.OptionRepository;
import com.fdb.backend.Repositories.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public boolean existsById(int id) {
        return questionRepository.existsById(id);
    }

    public Question getById(int id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.orElse(null);
    }

    public List<Option> getAllOptionsByQuestionId(int questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {
            return question.getOptions();
        }
        return null;
    }

    public Question saveQuestionWithOptions(Question questionWithOptions) {
        // Save the question
        Question savedQuestion = questionRepository.save(questionWithOptions);

        // Set the question for each option and save them
        List<Option> options = savedQuestion.getOptions();
        for (Option option : options) {
            option.setQuestion(savedQuestion);
            optionRepository.save(option);
        }

        return savedQuestion;
    }

    public List<Media> getMediaByQuestionId(int questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId))
                .getMediaList();
    }
}
