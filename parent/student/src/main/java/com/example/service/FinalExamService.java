package com.example.service;

import com.example.pojo.FinalExam;
import com.example.repository.FinalExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalExamService {
    @Autowired
    private FinalExamRepository finalExamRepository;

    public void setFinalExamRepository(FinalExamRepository finalExamRepository) {
        this.finalExamRepository = finalExamRepository;
    }

    public void saveFinalExam(FinalExam finalExam) {
        finalExamRepository.save(finalExam);
    }

    public List<FinalExam> getFinalExamList() {
        return finalExamRepository.findAll();
    }
}
