package com.example.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "final_exam")
@IdClass(FinalExamId.class)
public class FinalExam {
    @Id
    @Column(name = "exam_id", nullable = false, length = 20)
    private String examId;

    @Column(name = "major_id", nullable = false, length = 10)
    private String majorId;

    @Id
    @Column(name = "exam_time", nullable = false, length = 20)
    private String examTime;
    @Column(name = "course_id", nullable = false)
    private String courseId;

    @Column(name = "exam_format", length = 10)
    private String examFormat;

    @Id
    @Column(name = "classroom_name", length = 20)
    private String classroomName;

    public String getExamId() {
        return examId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamFormat() {
        return examFormat;
    }

    public void setExamFormat(String examFormat) {
        this.examFormat = examFormat;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

}