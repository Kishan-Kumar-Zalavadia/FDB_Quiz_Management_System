package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Result;
import com.fdb.backend.Services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
@CrossOrigin("*")
public class ResultController {
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }


}
