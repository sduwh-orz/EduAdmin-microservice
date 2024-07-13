package com.example.repository;

import com.example.pojo.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, String>, JpaSpecificationExecutor<StudentInfo> {

}