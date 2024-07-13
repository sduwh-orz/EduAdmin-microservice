package com.example.util;

import com.example.pojo.Classroom;
import com.example.repository.ClassroomRepository;
import com.mifmif.common.regex.Generex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/init")
public class classroomInit {
    @Autowired
    private ClassroomRepository classroomRepository;
    @GetMapping("/initClassroom")
    public void init() {
        Generex generexName = new Generex("(图西教学楼|电子楼|文学楼|商学院) [1-2]0[1-5]");
        Generex generexTime = new Generex("(Mon|Tue|Wed|Thu|Fri),(08:00-09:50|10:10-12:00|14:30-16:20|16:40-18:30)");
        List<String> nameList = generexName.getAllMatchedStrings();
        List<String> timeList = generexTime.getAllMatchedStrings();
        for(int i = 0;i < nameList.size();i++) {
            for (String s : timeList) {
                Classroom classroom = new Classroom();
                classroom.setClassroomId(String.valueOf(5000 + i));
                classroom.setClassroomName(nameList.get(i));
                classroom.setFreeTime(s);
                classroom.setFreeNow(1);
                classroomRepository.save(classroom);
            }
        }
    }
}
