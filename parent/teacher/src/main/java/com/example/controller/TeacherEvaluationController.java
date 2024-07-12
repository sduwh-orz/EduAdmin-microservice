package com.example.controller;

import com.example.pojo.User;
import com.example.repository.TeacherEvaluationRepository;
import com.example.response.Response;
import com.example.service.TeacherEvaluationService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/teacherevaluation")
public class TeacherEvaluationController {

    @Autowired
    private UserService userService;
    @Autowired
    private TeacherEvaluationService teacherEvaluationService;
    private final TeacherEvaluationRepository teacherEvaluationRepository;

    public TeacherEvaluationController(TeacherEvaluationService teacherEvaluationService,
                                       TeacherEvaluationRepository teacherEvaluationRepository) {
        this.teacherEvaluationService = teacherEvaluationService;
        this.teacherEvaluationRepository = teacherEvaluationRepository;
    }

    @PostMapping(path = "/viewteacherscore")
    public synchronized Response viewteacherscore(
            @RequestParam String courseId,
            HttpSession httpSession) {
        // 已经默认登录！！！！！！！！！！！！！！！！
        User user = userService.getUser((String) httpSession.getAttribute("user"));
        String userId = user.getUserId();
        return new Response(true, "", teacherEvaluationService.getTeacherEvaluationsByUserId(userId));

    }
}
