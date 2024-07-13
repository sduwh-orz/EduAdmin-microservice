package com.example.service;

import com.example.dto.FinalCourseDTO;
import com.example.pojo.FinalCourse;
import com.example.pojo.StudentCourses;
import com.example.repository.FinalCourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinalCourseService {
    @Autowired
    private FinalCourseRepository finalCourseRepository;

    public void setFinalCourseRepository(FinalCourseRepository finalCourseRepository) {
        this.finalCourseRepository = finalCourseRepository;
    }

    public List<FinalCourse> getFinalCoursesByUserId(String userId){
        return finalCourseRepository.getFinalCoursesByUserId(userId);
    }
    public static FinalCourseDTO toDTO(FinalCourse original) {
        FinalCourseDTO bean = new FinalCourseDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    public void saveFinalCourse(FinalCourse finalCourse) {
        finalCourseRepository.save(finalCourse);
    }

    public List<FinalCourse> getFinalCourseList() {
        return finalCourseRepository.getFinalCourseList();
    }
    public List<FinalCourse> findCourseByCourses(List<StudentCourses> courses) {
        List<String> courseIds = new ArrayList<>();
        for(StudentCourses course : courses) {
            courseIds.add(course.getCourseId());
        }
        List<FinalCourse> courseList = new ArrayList<>();
        for(String courseId : courseIds) {
            courseList.addAll(finalCourseRepository.getCourseByCourseId(courseId));
        }
        return courseList;
    }
}
