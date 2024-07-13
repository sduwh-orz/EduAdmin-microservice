package com.example.service;

import com.example.dto.ScoreDTO;
import com.example.pojo.Score;
import com.example.repository.ScoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository ScoreRepository;

    public void setScoreRepository(ScoreRepository scoreRepository) {
        ScoreRepository = scoreRepository;
    }

    public void setScoreByUserIdAndCourseId(Integer score, String courseId, String userId) {
        Score score1 = new Score();
        score1.setScore(score);
        score1.setUserId(userId);
        score1.setCourseId(courseId);
        ScoreRepository.save(score1);
    }

    public static ScoreDTO toDTO(Score original) {
        ScoreDTO bean = new ScoreDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }
}