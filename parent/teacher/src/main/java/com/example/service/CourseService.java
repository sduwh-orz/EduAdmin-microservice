package com.example.service;

import com.example.dto.CourseDTO;
import com.example.pojo.Course;
import com.example.pojo.CourseApplication;
import com.example.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public static CourseDTO toDTO(Course original) {
        CourseDTO bean = new CourseDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> getCourseList() {
        return courseRepository.getCourseList();
    }

    public void updateNumToZeroFinal() {
        courseRepository.updateNumToZeroFinal();
    }

    public List<Course> getOneCourseList() {
        return courseRepository.getOneCourseList();
    }

    public void applyCourseByCourseIdNameUserIdAndCourseNum(
            String courseName,
            String userId,
            Integer courseNum){

        int tot = courseRepository.countCourses();

        Course course = new Course();
        course.setCourseId(String.valueOf(tot + 1));
        course.setCourseName(courseName);
        course.setCourseTeacherId(userId);
        course.setCourseNum(courseNum);
        courseRepository.save(course);
    }
}
