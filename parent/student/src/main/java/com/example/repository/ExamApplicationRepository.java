package com.example.repository;

import com.example.pojo.ExamApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamApplicationRepository extends JpaRepository<ExamApplication, String> {
    @Query("select count(1) from ExamApplication e")
    int countExamApplications();
}
