package com.example.controller;

import com.example.pojo.Course;
import com.example.pojo.CourseApplication;
import com.example.pojo.User;
import com.example.repository.UserRepository;
import com.example.response.Response;
import com.example.service.CourseApplicationService;
import com.example.service.CourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin
@RequestMapping(path = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseApplicationService courseApplicationService;
    @Autowired
    private UserRepository userRepository;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/accept")
    public synchronized Response acceptCourse(HttpSession session, @RequestParam String courseId) {
        if(session.getAttribute("user") == null)
            return new Response(false, "Please login first", null);
        User user = userRepository.findById((String) session.getAttribute("user")).orElse(null);
        if(!user.getUserType().equals("admin"))
            return new Response(false, "Permission denied", null);
        if(courseApplicationService.findById(courseId) == null)
            return new Response(false, "Course not found", null);
        try {
            CourseApplication courseApplication = courseApplicationService.findById(courseId);
            Course course = new Course();
            course.setCourseId(courseId);
            course.setCourseName(courseApplication.getCourseName());
            course.setCourseTeacherId(courseApplication.getCourseTeacherId());
            course.setCourseNum(courseApplication.getCourseNum());
            courseService.saveCourse(course);
            courseApplicationService.deleteCourseApplication(courseId);
            return new Response(true, "Accept successfully", null);
        } catch (Exception e) {
            return new Response(false, "Accept failed", null);
        }
    }

    @PostMapping("/reject")
    public synchronized Response rejectCourse(HttpSession session, @RequestParam String courseId) {
        if(session.getAttribute("user") == null)
            return new Response(false, "Please login first", null);
        User user = userRepository.findById((String) session.getAttribute("user")).orElse(null);
        if(!user.getUserType().equals("admin"))
            return new Response(false, "Permission denied", null);
        if(courseApplicationService.findById(courseId) == null)
            return new Response(false, "Course not found", null);
        if(courseApplicationService.deleteCourseApplication(courseId))
            return new Response(true, "Reject successfully", null);
        return new Response(false, "Reject failed", null);
    }

    @GetMapping("/getCourseList")
    public synchronized Response getCourseList() {
        return new Response(true, "", courseService.getCourseList());
    }

    @GetMapping("/listApplication")
    public synchronized Response getApplicationList() {
        return new Response(true, "", courseApplicationService.getCourseApplicationList());
    }

    @GetMapping("/getOneCourseList")
    public synchronized Response getOneCourseList() {
        return new Response(true, "", courseService.getOneCourseList());
    }
}
