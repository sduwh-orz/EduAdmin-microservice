package com.example.service;

import com.example.pojo.ClassroomApplication;
import com.example.repository.ClassroomApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomApplicationService {
    @Autowired
    private ClassroomApplicationRepository classroomApplicationRepository;

    public List<ClassroomApplication> listApplication() {
        return classroomApplicationRepository.findAll();
    }

    public void setClassroomApplicationRepository(ClassroomApplicationRepository classroomApplicationRepository) {
        this.classroomApplicationRepository = classroomApplicationRepository;
    }
    public void deleteApplicationByUserIdAndClassroomIdAndNeedTime(String userId, String classroomId, String needTime) {
        classroomApplicationRepository.deleteApplicationByUserIdAndClassroomIdAndNeedTime(userId, classroomId, needTime);
    }

    public void applyClassroomByUserIdClassroomIdReasonAndFreeTime(
            String userId,
            String classroomId,
            String reason,
            String needTime){
        ClassroomApplication CA1 = new ClassroomApplication();
        CA1.setUserId(userId);
        CA1.setClassroomId(classroomId);
        CA1.setReason(reason);
        CA1.setNeedTime(needTime);
        classroomApplicationRepository.save(CA1);
    }
}
