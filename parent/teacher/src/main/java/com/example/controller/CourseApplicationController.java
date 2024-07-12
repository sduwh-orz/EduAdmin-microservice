package com.example.controller;

import com.example.pojo.User;
import com.example.response.Response;
import com.example.service.CourseApplicationService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/courseApplication")
public class CourseApplicationController {
    @Autowired
    private CourseApplicationService courseApplicationService;
    @Autowired
    private UserService userService;

//    public void setCourseService(CourseService courseService) {
//        this.courseService = courseService;
//    }

    public CourseApplicationController(CourseApplicationService courseApplicationService) {
        this.courseApplicationService = courseApplicationService;
    }

    @PostMapping(path = "/courseApplication")
    public synchronized Response courseApplication(
            @RequestParam String courseName,
            @RequestParam Integer courseNum,
            HttpSession httpSession){
        if (httpSession.getAttribute("user") == null) {
            return new Response(false, "尚未登录", null);
        } else {
            User user = userService.getUser((String) httpSession.getAttribute("user"));
            String userId = user.getUserId();
            courseApplicationService.applyCourseByCourseIdNameUserIdAndCourseNum(courseName, userId, courseNum);
            return new Response(true, "", null);
        }
    }
}
