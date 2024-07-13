package com.example.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.io.Serializable;

@Entity
@IdClass(StudentCoursesId.class)
public class StudentCourses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String studentId;

    @Id
    private String courseId;

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "StudentCourses{" +
                "studentId=" + studentId + '\'' +
                "courseId=" + courseId + '\'' +
                '}';
    }
}
