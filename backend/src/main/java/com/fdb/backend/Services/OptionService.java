package com.fdb.backend.Services;

import com.fdb.backend.Entities.Option;
import com.fdb.backend.Entities.Question;
import com.fdb.backend.Repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Autowired
    private QuestionService questionService;

    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findByQuestionQuestionId(questionId);
    }

    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    public boolean assignOptionToQuestion(int optionId, int questionId) {
        Option option = optionRepository.findById(optionId).orElse(null);
        if (option != null) {
            Question question = questionService.getById(questionId);
            if (question != null) {
                option.setQuestion(question);
                optionRepository.save(option);
                return true;
            }
        }
        return false;
    }


    public int calculateScore(List<Integer> selectedOptionIds) {
        int score = 0;
        for (Integer optionId : selectedOptionIds) {
            Option option = optionRepository.findById(optionId).orElse(null);
            if (option != null && option.isCorrect() && option.getQuestion() != null) {
                score += option.getQuestion().getQuestionMarks();
            }
        }
        return score;
    }
}
