package com.example.pojo;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "teacher_evaluation")
@IdClass(TeacherEvaluationId.class)
public class TeacherEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_id")
    private String courseId;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "teacher_score", nullable = false)
    private String teacherScore;

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setTeacherScore(String teacherScore) {
        this.teacherScore = teacherScore;
    }

    public String getTeacherScore() {
        return teacherScore;
    }

    @Override
    public String toString() {
        return "TeacherEvaluation{" +
                "courseId=" + courseId + '\'' +
                "userId=" + userId + '\'' +
                "teacherScore=" + teacherScore + '\'' +
                '}';
    }
}
