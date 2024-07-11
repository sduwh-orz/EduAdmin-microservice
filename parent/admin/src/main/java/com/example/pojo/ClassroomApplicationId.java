package com.example.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ClassroomApplicationId implements Serializable {
    @Column(name = "classroom_id", nullable = false)
    private String classroomId;
    @Column(name = "need_time", nullable = false)
    private String needTime;

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime;
    }
}
