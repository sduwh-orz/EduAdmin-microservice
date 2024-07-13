package com.example.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "course_id", nullable = false)
    private String courseId;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "course_teacher_id", nullable = false)
    private String courseTeacherId;
    @Column(name = "course_num", nullable = false)
    private int courseNum;

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getCourseTeacherId() {
        return courseTeacherId;
    }

    public void setCourseTeacherId(String courseTeacherId) {
        this.courseTeacherId = courseTeacherId;
    }

}
