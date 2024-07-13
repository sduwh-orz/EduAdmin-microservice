package com.example.controller;

import com.example.pojo.User;
import com.example.response.Response;
import com.example.service.ClassroomApplicationService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/classroomapplication")
public class ClassroomApplicationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ClassroomApplicationService classroomApplicationService;

    public ClassroomApplicationController(ClassroomApplicationService classroomApplicationService) {
        this.classroomApplicationService = classroomApplicationService;
    }

    @PostMapping(path = "/classroomapplication")
    public synchronized Response classroomapplication(
            //@RequestParam String userId,
            @RequestParam String classroomId,
            @RequestParam String reason,
            @RequestParam String needTime,
            HttpSession httpSession){
        User user =  userService.getUser((String) httpSession.getAttribute("user"));
        String userId = user.getUserId();
        classroomApplicationService.applyClassroomByUserIdClassroomIdReasonAndFreeTime(
                userId,
                classroomId,
                reason,
                needTime);
        return new Response(true, "", null);
    }
}
