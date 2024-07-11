package com.example.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "classroom")
@IdClass(ClassroomId.class)
public class Classroom {

    @Id
    @Column(name = "classroom_id")
    private String classroomId;
    @Column(name = "classroom_name", nullable = false)
    private String classroomName;
    @Id
    @Column(name = "free_time")
    private String freeTime;
    @Column(name = "free_now", nullable = false)
    private Integer freeNow;


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


    public Integer getFreeNow() {
        return freeNow;
    }

    public void setFreeNow(Integer freeNow) {
        this.freeNow = freeNow;
    }

}
