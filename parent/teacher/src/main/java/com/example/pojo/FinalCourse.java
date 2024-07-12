package com.example.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "final_course")
@IdClass(FinalCourseId.class)
public class FinalCourse {

    @Id
    @Column(name = "course_id", nullable = false)
    private String courseId;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "course_teacher_id", nullable = false)
    private String courseTeacherId;
    @Column(name = "course_teacher", nullable = false)
    private String courseTeacher;
    @Id
    @Column(name = "classroom_id", nullable = false)
    private String classroomId;
    @Column(name = "classroom_name", nullable = false)
    private String classroomName;
    @Id
    @Column(name = "free_time", nullable = false)
    private String freeTime;


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

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }
}
