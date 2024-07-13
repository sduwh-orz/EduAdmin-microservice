package com.example.controller;

import com.example.pojo.Classroom;
import com.example.pojo.ExamApplication;
import com.example.pojo.FinalExam;
import com.example.response.Response;
import com.example.service.ClassroomService;
import com.example.service.ExamApplicationService;
import com.example.service.FinalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/finalExam")
public class FinalExamController {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private FinalExamService finalExamService;
    @Autowired
    private ExamApplicationService examApplicationService;
    Random random = new Random();

    @PostMapping("/arrange")
    public synchronized Response arrange() {
        Map<String, Set<String>> mapForNotAvailableMajorTime = new HashMap<>();
        List<ExamApplication> examList = examApplicationService.listAllApplication();
        List<Classroom> classroomList = classroomService.getClassroomList();
        while(!classroomList.isEmpty() && !examList.isEmpty()) {
            int tmpIndex = random.nextInt(classroomList.size());
            Classroom classroom = classroomList.get(tmpIndex);
            ExamApplication exam = examList.get(0);
            String majorId = exam.getMajorId();
            if(!mapForNotAvailableMajorTime.containsKey(majorId)) {
                mapForNotAvailableMajorTime.put(majorId, new HashSet<>());
            }
            Set<String> notAvailableTime = mapForNotAvailableMajorTime.get(majorId);
            while(notAvailableTime.contains(classroom.getFreeTime())) {
                tmpIndex = random.nextInt(classroomList.size());
                classroom = classroomList.get(tmpIndex);
            }
            if(classroom.getFreeNow().equals(1)) {
                FinalExam finalExam = new FinalExam();
                finalExam.setExamId(exam.getExamId());
                finalExam.setMajorId(exam.getMajorId());
                finalExam.setExamTime(classroom.getFreeTime());
                finalExam.setClassroomName(classroom.getClassroomName());
                finalExam.setExamFormat(exam.getExamFormat());
                finalExam.setCourseId(exam.getCourseId());
                finalExamService.saveFinalExam(finalExam);
                classroomService.updateFreeNowToZeroByClassroomIdAndClassroomFreeTime(classroom.getClassroomId(), classroom.getFreeTime());
                mapForNotAvailableMajorTime.get(majorId).add(classroom.getFreeTime());
                classroomList.remove(tmpIndex);
                examList.remove(0);
                examApplicationService.deleteByExamId(exam.getExamId());
            }
        }
        return new Response(true, "", null);
    }

    @GetMapping("/getFinalExamList")
    public synchronized Response getFinalExamList() {
        return new Response(true, "", finalExamService.getFinalExamList());
    }
}
