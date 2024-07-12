package com.example.controller;

import com.example.response.Response;
import com.example.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping(path = "/recordscore")
    public synchronized Response recordscore(
            @RequestParam Integer score,
            @RequestParam String courseId,
            @RequestParam String userId) {
        scoreService.setScoreByUserIdAndCourseId(score, courseId, userId);
        return new Response(true, "", null);
    }
}
