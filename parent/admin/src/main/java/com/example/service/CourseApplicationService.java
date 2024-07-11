package com.example.service;

import com.example.pojo.CourseApplication;
import com.example.repository.CourseApplicationRepository;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseApplicationService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseApplicationRepository courseApplicationRepository;

    public CourseApplication findById(String courseId) {
        return courseApplicationRepository.findById(courseId).orElse(null);
    }
    public void setCourseApplicationRepository(CourseApplicationRepository courseApplicationRepository) {
        this.courseApplicationRepository = courseApplicationRepository;
    }

    public boolean deleteCourseApplication(String courseId) {
        try {
            courseApplicationRepository.deleteById(courseId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void applyCourseByCourseIdNameUserIdAndCourseNum(
            String courseName,
            String userId,
            Integer courseNum){

        int courseCount = courseRepository.countCourses();
        int courseApplicationCount = courseApplicationRepository.countCourseApplications();

        String courseId = String.valueOf(4001 + courseCount + courseApplicationCount);

        CourseApplication courseA1 = new CourseApplication();
        courseA1.setCourseId(courseId);
        courseA1.setCourseName(courseName);
        courseA1.setCourseTeacherId(userId);
        courseA1.setCourseNum(courseNum);
        courseApplicationRepository.save(courseA1);
    }

    public List<CourseApplication> getCourseApplicationList() {
        return courseApplicationRepository.getCourseApplicationList();
    }
}
