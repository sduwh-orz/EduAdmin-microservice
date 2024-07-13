package com.example.controller;

import com.example.pojo.Classroom;
import com.example.pojo.Course;
import com.example.pojo.FinalCourse;
import com.example.pojo.User;
import com.example.response.Response;
import com.example.service.ClassroomService;
import com.example.service.CourseService;
import com.example.service.FinalCourseService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/finalCourse")
public class FinalCourseController {
    @Autowired
    private UserService userService;
    @Autowired
    private FinalCourseService finalCourseService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassroomService classroomService;
    Random random = new Random();

    public FinalCourseController(FinalCourseService finalCourseService) {
        this.finalCourseService = finalCourseService;
    }

    @PostMapping(path = "/arrange")
    public synchronized Response arrange() {

        List<Course> courseList = courseService.getCourseList();
        List<Classroom> classroomList = classroomService.getClassroomList();

        Map<String, Set<String>> mapForNotAvailableTeacherTime = new HashMap<>();

        for(Course c: courseList) {
            int t = c.getCourseNum();
            String teacherId = c.getCourseTeacherId();
            if(!mapForNotAvailableTeacherTime.containsKey(teacherId)) {
                mapForNotAvailableTeacherTime.put(teacherId, new HashSet<>());
            }
            Set<String> notAvailableTime = mapForNotAvailableTeacherTime.get(teacherId);
            for(int i = 1;i <= t;i++) {
                int tmpIndex = random.nextInt(classroomList.size());
                Classroom classroom = classroomList.get(tmpIndex);
                // check if the classroom is free now and the teacher is available
                while(notAvailableTime.contains(classroom.getFreeTime())) {
                    tmpIndex = random.nextInt(classroomList.size());
                    classroom = classroomList.get(tmpIndex);
                }
                if(classroom.getFreeNow().equals(1)) {
                    FinalCourse finalCourse = new FinalCourse();
                    finalCourse.setCourseId(c.getCourseId());
                    finalCourse.setCourseName(c.getCourseName());
                    finalCourse.setCourseTeacherId(c.getCourseTeacherId());
                    finalCourse.setClassroomId(classroom.getClassroomId());
                    finalCourse.setClassroomName(classroom.getClassroomName());
                    finalCourse.setFreeTime(classroom.getFreeTime());
                    finalCourse.setCourseTeacher(userService.getUser(teacherId).getUserName());
                    finalCourseService.saveFinalCourse(finalCourse);
                    classroomService.updateFreeNowToZeroByClassroomIdAndClassroomFreeTime(classroom.getClassroomId(), classroom.getFreeTime());
                    mapForNotAvailableTeacherTime.get(teacherId).add(classroom.getFreeTime());
                    classroomList.remove(tmpIndex);
                }
            }
            c.setCourseNum(0);
        }
        courseService.updateNumToZeroFinal();
        return new Response(true, "", null);
    }

    @PostMapping(path = "/viewcourse")
    public synchronized Response viewcourse(HttpSession httpSession){
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        User user =  userService.getUser((String) httpSession.getAttribute("user"));
        String userId = user.getUserId();
        return new Response(true, "", finalCourseService.getFinalCoursesByUserId(userId));
    }

    @GetMapping("/getFinalCourseList")
    public synchronized Response getFinalCourseList() {
        return new Response(true, "", finalCourseService.getFinalCourseList());
    }
}
