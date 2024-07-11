package com.example.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "classroom_application")
@IdClass(ClassroomApplicationId.class)
public class ClassroomApplication {
    @Id
    @Column(name = "classroom_id")
    private String classroomId;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Id
    @Column(name = "need_time")
    private String needTime;
    @Column(name = "reason", nullable = false)
    private String reason;

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
