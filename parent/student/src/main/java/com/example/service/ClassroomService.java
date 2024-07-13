package com.example.service;

import com.example.dto.ClassroomDTO;
import com.example.pojo.Classroom;
import com.example.repository.ClassroomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    public void setClassroomRepository(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public static ClassroomDTO toDTO(Classroom original) {
        ClassroomDTO bean = new ClassroomDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    public List<Classroom> getClassroomList() {
        return classroomRepository.getClassroomList();
    }

    public void updateFreeNowToZeroByClassroomIdAndClassroomFreeTime(String classroomId, String freeTime) {
        classroomRepository.updateFreeNowToZeroByClassroomIdAndClassroomFreeTime(classroomId, freeTime);
    }

    public Classroom getClassroomByClassroomIdAndFreeTime(String classroomId, String freeTime) {
        return classroomRepository.getClassroomByClassroomIdAndFreeTime(classroomId, freeTime);
    }

    public List<Classroom> getClassroomsByClassroomNameAndFreeTime(String classroomName, String day) {
        return classroomRepository.getClassroomsByClassroomNameAndFreeTime(classroomName, day);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.getAllClassrooms();
    }
}
