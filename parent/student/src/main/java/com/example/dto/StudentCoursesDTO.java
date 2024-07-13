package com.example.dto;


import java.io.Serializable;

public class StudentCoursesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String studentId;

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
