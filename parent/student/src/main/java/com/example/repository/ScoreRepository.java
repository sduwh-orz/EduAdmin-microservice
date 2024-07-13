package com.example.repository;

import com.example.pojo.Score;
import com.example.pojo.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, ScoreId>, JpaSpecificationExecutor<Score> {
    List<Score> findByUserId(String userId);
}