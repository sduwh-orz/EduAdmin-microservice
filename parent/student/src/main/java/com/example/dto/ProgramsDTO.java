package com.example.dto;


import java.io.Serializable;
import java.util.Date;

public class ProgramsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer programId;

    private Integer majorId;

    private Integer years;

    private Date auditTime;

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
