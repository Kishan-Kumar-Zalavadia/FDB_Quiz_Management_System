package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}