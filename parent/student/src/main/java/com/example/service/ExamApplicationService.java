package com.example.service;

import com.example.pojo.ExamApplication;
import com.example.repository.ExamApplicationRepository;
import com.example.repository.ExamRepository;
import com.example.repository.FinalExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamApplicationService {
    @Autowired
    private ExamApplicationRepository ExamApplicationRepository;
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private FinalExamRepository finalExamRepository;

    public void setExamApplicationRepository(com.example.repository.ExamApplicationRepository examApplicationRepository) {
        ExamApplicationRepository = examApplicationRepository;
    }

    public void applyExamByMajorCourseAndFormat(
            String majorId,
            String courseId,
            String examFormat){

        int finalExamCount = finalExamRepository.countFinalExams();
        int examApplicationCount = ExamApplicationRepository.countExamApplications();

        String examId = String.valueOf(6000 + finalExamCount + examApplicationCount);

        ExamApplication EA1 = new ExamApplication();
        EA1.setExamId(examId);
        EA1.setMajorId(majorId);
        EA1.setCourseId(courseId);
        EA1.setExamFormat(examFormat);
        ExamApplicationRepository.save(EA1);
    }

    public List<ExamApplication> listAllApplication() {
        return ExamApplicationRepository.findAll();
    }
}
