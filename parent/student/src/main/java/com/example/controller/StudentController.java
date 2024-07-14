package com.example.controller;

import com.example.pojo.StudentCourses;
import com.example.pojo.TeacherEvaluation;
import com.example.repository.*;
import com.example.response.Response;
import com.example.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin
@RequestMapping(path = "/student")
public class StudentController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private ProgramsRepository programsRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentCoursesRepository studentCoursesRepository;
    @Autowired
    private TeacherEvaluationRepository teacherEvaluationRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FinalCourseRepository finalCourseRepository;
    @Autowired
    private FinalCourseService finalCourseService;
    @Autowired
    private StudentSelectionService studentSelectionService;
    @Autowired
    private StudentCourseSelectionMsgSender studentCourseSelectionMsgSender;
    @Autowired
    HttpSession httpSession;

    // 课程表查询
    @GetMapping("/courses")
    public Response getCourses() {
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        try {
            var courses = studentCoursesRepository.findByStudentId((String) httpSession.getAttribute("user"));
            return new Response(true, "", finalCourseService.findCourseByCourses(courses));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    // 空闲教室查询
    @GetMapping("/classroom")
    public Response getClassroom() {
        try {
            return new Response(true, "", classroomRepository.findAll().stream().filter(it -> it.getFreeNow() == 1).map(ClassroomService::toDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    // 成绩查询
    @GetMapping("/score")
    public Response getScore() {
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        try {
            return new Response(true, "", scoreRepository.findByUserId((String) httpSession.getAttribute("user")).stream().map(ScoreService::toDTO).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    // 评教信息获取。返回未评教的课程列表
    @GetMapping("/evaluation")
    public Response getEvaluation() {
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        try {
            var user = (String) httpSession.getAttribute("user");
            var evaluation = teacherEvaluationRepository.findByUserId(user);
            var courses = studentCoursesRepository.findByStudentId(user).stream().filter(
                    a -> evaluation.stream().noneMatch(
                            b -> a.getCourseId().equals(b.getCourseId())
                    )
            ).map(it -> CourseService.toDTO(courseRepository.getReferenceById(it.getCourseId())));
            return new Response(true, "", courses);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    // 提交单条评教
    @PostMapping("/set_evaluation")
    public Response setEvaluation(@RequestParam String courseId, @RequestParam String score) {
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        try {
            var user = (String) httpSession.getAttribute("user");
            var evaluation = new TeacherEvaluation();
            evaluation.setCourseId(courseId);
            evaluation.setUserId(user);
            evaluation.setTeacherScore(score);
            teacherEvaluationRepository.save(evaluation);
            return new Response(true, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    // 选课课程表查询。注意！！对于已选中的不应使用/courses查询，而是/selected_courses
    @GetMapping("/selectable")
    public Response getSelectableCourses() {
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        try {
            var selected = studentCoursesRepository.findByStudentId((String) httpSession.getAttribute("user"));
            var selectable = courseRepository.findAll().stream()
                    .filter(a -> selected.stream().noneMatch(
                            b -> a.getCourseId().equals(b.getCourseId())
                    ))
                    .map(CourseService::toDTO).collect(Collectors.toList());
            return new Response(true, "", selectable);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    // 提交选课
    // 2024.7.14：改用消息队列实现
    @PostMapping("/select_course")
    public Response selectCourse(@RequestParam List<String> courseIdList) {
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        try {
            var user = (String) httpSession.getAttribute("user");
            for (String courseId : courseIdList) {
                var evaluation = new StudentCourses();
                evaluation.setCourseId(courseId);
                evaluation.setStudentId(user);
                studentCourseSelectionMsgSender.studentCourseSelectionSend(evaluation);
            }
            studentSelectionService.saveStudentSelection((String) httpSession.getAttribute("user"));
            return new Response(true, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    // 提交选课：测试多用户同时提交选课对消息队列的并发性
    @GetMapping("/test")
    public Response test(@RequestParam String user) {
        try {
            var evaluation = new StudentCourses();
            evaluation.setCourseId("1");
            evaluation.setStudentId(user);
            studentCourseSelectionMsgSender.studentCourseSelectionSend(evaluation);
            studentSelectionService.saveStudentSelection(user);
            return new Response(true, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }
    // 选课系统中已选查询
    @GetMapping("/selected_courses")
    public Response getSelectedCourses() {
        if (httpSession.getAttribute("user") == null)
            return new Response(false, "未登录", null);
        try {
            var courses = studentCoursesRepository.findByStudentId((String) httpSession.getAttribute("user"));
            return new Response(true, "", courses.stream()
                    .map(it -> courseRepository.getReferenceById(it.getCourseId()))
                    .map(CourseService::toDTO)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "服务器错误", null);
        }
    }

    @GetMapping(path = "getCourseSelection")
    public Response getCourseSelection() {
        return new Response(true, "", studentSelectionService.getStudentSelectionByUserId((String) httpSession.getAttribute("user")));
    }


}
