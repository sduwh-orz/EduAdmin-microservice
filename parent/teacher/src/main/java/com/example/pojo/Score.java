package com.example.pojo;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "score")
@IdClass(ScoreId.class)
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "course_id", nullable = false)
    private String courseId;

    @Column(name = "score", nullable = false)
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
