package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Option;
import com.fdb.backend.Services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
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
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOption);
    }

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
}
