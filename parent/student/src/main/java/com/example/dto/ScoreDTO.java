package com.example.dto;

import java.io.Serializable;

public class ScoreDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String courseId;

    private Integer score;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "userId=" + userId + '\'' +
                "courseId=" + courseId + '\'' +
                "score=" + score + '\'' +
                '}';
    }


}
