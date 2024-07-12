package com.example.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ClassroomId implements Serializable {

    @Column(name = "classroom_id", nullable = false)
    private String classroomId;
    @Column(name = "free_time", nullable = false)
    private String freeTime;

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }
}
