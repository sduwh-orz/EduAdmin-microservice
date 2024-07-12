package com.example.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StudentCoursesId implements Serializable {

    @Column(name = "student_id", nullable = false)
    private String studentId;
    @Column(name = "course_id", nullable = false)
    private String courseId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
