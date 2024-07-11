package com.example.service;

import com.example.pojo.ExamApplication;
import com.example.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public void setExamRepository(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<ExamApplication> getExamList() {
        return examRepository.findAll();
    }

    public void deleteExamByExamId(String examId) {
        examRepository.deleteByExamId(examId);
    }
}
