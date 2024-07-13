package com.example.vo;


import java.io.Serializable;


public class ClassroomVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String classroomId;

    private String classroomName;

    private String emptyTime;

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

    public String getEmptyTime() {
        return emptyTime;
    }

    public void setEmptyTime(String emptyTime) {
        this.emptyTime = emptyTime;
    }
}
