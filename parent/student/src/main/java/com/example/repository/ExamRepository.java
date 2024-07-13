package com.example.repository;

import com.example.pojo.ExamApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExamRepository extends JpaRepository<ExamApplication, String> {
    @Query("select e from ExamApplication e where e.examId = ?1")
    ExamApplication getExamApplicationByExamId(String examId);

    @Transactional
    @Modifying
    @Query("delete from ExamApplication e where e.examId = ?1")
    void deleteByExamId(String examId);
}
