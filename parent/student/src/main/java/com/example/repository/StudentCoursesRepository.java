package com.example.repository;

import com.example.pojo.StudentCourses;
import com.example.pojo.StudentCoursesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentCoursesRepository extends JpaRepository<StudentCourses, StudentCoursesId>, JpaSpecificationExecutor<StudentCourses> {
    List<StudentCourses> findByStudentId(String studentId);

}