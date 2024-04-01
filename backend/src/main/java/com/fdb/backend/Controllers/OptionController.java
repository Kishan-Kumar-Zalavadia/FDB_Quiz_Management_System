package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Option;
import com.fdb.backend.Services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping("/save")
    public ResponseEntity<Option> saveOption(@RequestBody Option option) {
        Option savedOption = optionService.saveOption(option);
        System.out.println("Option from angular: "+savedOption.isCorrect());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOption);
    }

//    @PostMapping("/save")
//    public ResponseEntity<Option> saveOption(@RequestBody Option optionDTO) {
//        Option option = converttoEntity(optionDTO); // Convert DTO to entity if needed
//        Option savedOption = optionService.saveOption(option);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedOption);
//    }
//
//    private Option convertDTOtoEntity(Option optionDTO) {
//        Option option = new Option();
//        option.setOptionId(optionDTO.getOptionId());
//        option.setOptionName(optionDTO.getOptionName());
//        option.setCorrect(optionDTO.isCorrect());
//        return option;
//    }


    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Option>> getOptionsByQuestionId(@PathVariable Long questionId) {
        List<Option> options = optionService.getOptionsByQuestionId(questionId);
        if (options.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(options);
        }
    }

    @PutMapping("/{optionId}/assignToQuestion/{questionId}")
    public ResponseEntity<String> assignOptionToQuestion(@PathVariable int optionId, @PathVariable int questionId) {
        if (optionService.assignOptionToQuestion(optionId, questionId)) {
            return ResponseEntity.ok("Option assigned to question successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Option or Question not found");
        }
    }

    @PostMapping("/score")
    public int calculateScore(@RequestBody List<Integer> selectedOptionIds) {
        return optionService.calculateScore(selectedOptionIds);
    }
}
