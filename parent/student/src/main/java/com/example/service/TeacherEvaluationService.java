package com.example.service;

import com.example.pojo.TeacherEvaluation;
import com.example.repository.TeacherEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherEvaluationService {
    @Autowired
    private TeacherEvaluationRepository teacherEvaluationRepository;

    public void setTeacherEvaluationRepository(TeacherEvaluationRepository teacherEvaluationRepository) {
        this.teacherEvaluationRepository = teacherEvaluationRepository;
    }

    public List<TeacherEvaluation> getTeacherEvaluationsByUserId(String userId) {
        return teacherEvaluationRepository.getTeacherEvaluationsByUserId(userId);
    }
//    public List<TeacherEvaluation> findCourseIdByUserId(String userId){
//        return teacherEvaluationRepository.findCourseIdByUserId(userId);
//    }

}
