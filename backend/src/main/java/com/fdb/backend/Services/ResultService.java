package com.fdb.backend.Services;


import com.fdb.backend.Entities.Result;
import com.fdb.backend.Repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }
}
