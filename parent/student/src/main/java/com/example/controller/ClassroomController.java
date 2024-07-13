package com.example.controller;

import com.example.pojo.Classroom;
import com.example.pojo.ClassroomApplication;
import com.example.pojo.User;
import com.example.repository.UserRepository;
import com.example.response.Response;
import com.example.service.ClassroomApplicationService;
import com.example.service.ClassroomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping(path = "/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassroomApplicationService classroomApplicationService;

    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("/listApplication")
    public synchronized Response listApplication(HttpSession session) {
        if(session.getAttribute("user") == null)
            return new Response(false, "Please login first", null);
        User user = userRepository.findById((String) session.getAttribute("user")).orElse(null);
        if(!user.getUserType().equals("admin"))
            return new Response(false, "Permission denied", null);
        List<ClassroomApplication> classroomApplicationList = classroomApplicationService.listApplication();
        return new Response(true, "", classroomApplicationList);
    }

    @PostMapping("/accept")
    public synchronized Response acceptApplication(HttpSession session, @RequestParam String userId,
                                                   @RequestParam String classroomId, @RequestParam String needTime) {
        if(session.getAttribute("user") == null)
            return new Response(false, "Please login first", null);
        User user = userRepository.findById((String) session.getAttribute("user")).orElse(null);
        if(!user.getUserType().equals("admin"))
            return new Response(false, "Permission denied", null);
        Classroom classroom = classroomService.getClassroomByClassroomIdAndFreeTime(classroomId, needTime);
        if(classroom.getFreeNow().equals(1)) {
            classroomService.updateFreeNowToZeroByClassroomIdAndClassroomFreeTime(classroomId, needTime);
            classroomApplicationService.deleteApplicationByUserIdAndClassroomIdAndNeedTime(userId, classroomId, needTime);
            return new Response(true, "", null);
        }
        return new Response(false, "Classroom is not free now", null);
    }

    @PostMapping("/reject")
    public synchronized Response rejectApplication(HttpSession session, @RequestParam String userId,
                                                   @RequestParam String classroomId, @RequestParam String needTime) {
        if(session.getAttribute("user") == null)
            return new Response(false, "Please login first", null);
        User user = userRepository.findById((String) session.getAttribute("user")).orElse(null);
        if(!user.getUserType().equals("admin"))
            return new Response(false, "Permission denied", null);
        classroomApplicationService.deleteApplicationByUserIdAndClassroomIdAndNeedTime(userId, classroomId, needTime);
        return new Response(true, "", null);
    }

    @GetMapping(path = "/getAllClassrooms")
    public Response getAllClassrooms() {
        return new Response(true, "", classroomService.getAllClassrooms());
    }

    @PostMapping(path = "/getByWhereAndDay")
    public Response getClassroomsByClassroomNameAndFreeTime(@RequestParam String classroomName, @RequestParam String day) {
        return new Response(true, "", classroomService.getClassroomsByClassroomNameAndFreeTime(classroomName, day));
    }
}
