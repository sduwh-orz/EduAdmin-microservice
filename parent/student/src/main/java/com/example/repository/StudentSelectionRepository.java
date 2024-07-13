package com.example.repository;

import com.example.pojo.StudentSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSelectionRepository extends JpaRepository<StudentSelection, String> {

    @Query("select s from StudentSelection s where s.userId = ?1")
    StudentSelection getStudentSelectionByUserId(String userId);
}
