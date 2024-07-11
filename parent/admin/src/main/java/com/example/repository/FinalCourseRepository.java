package com.example.repository;

import com.example.pojo.FinalCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalCourseRepository extends JpaRepository<FinalCourse, String> {
    @Query("select t from FinalCourse t where t.courseTeacherId = ?1")
    List<FinalCourse> getFinalCoursesByUserId(String userId);

    @Query("select t from FinalCourse t")
    List<FinalCourse> getFinalCourseList();

    @Query("select t from FinalCourse t where t.courseId = ?1")
    List<FinalCourse> getCourseByCourseId(String courseId);
}
