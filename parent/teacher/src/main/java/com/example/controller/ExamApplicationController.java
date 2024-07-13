package com.example.controller;

import com.example.feign.IAdminService;
import com.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examApplication")
public class ExamApplicationController {
    @Autowired
    private IAdminService adminService;

    @PostMapping(path = "/examApplication")
    public synchronized Response examapplication(
            @RequestParam String majorId,
            @RequestParam String courseId,
            @RequestParam String examFormat
    ){
        return adminService.examapplication(majorId, courseId, examFormat);
    }
}
