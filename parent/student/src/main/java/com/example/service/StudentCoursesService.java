package com.example.service;

import com.example.dto.StudentCoursesDTO;
import com.example.pojo.StudentCourses;
import com.example.pojo.StudentCoursesId;
import com.example.repository.StudentCoursesRepository;
import com.example.vo.StudentCoursesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentCoursesService {

    @Autowired
    private StudentCoursesRepository studentCoursesRepository;

    public String save(StudentCoursesVO vO) {
        StudentCourses bean = new StudentCourses();
        BeanUtils.copyProperties(vO, bean);
        bean = studentCoursesRepository.save(bean);
        return bean.getStudentId();
    }

    public void delete(StudentCoursesId id) {
        studentCoursesRepository.deleteById(id);
    }

    public void update(StudentCoursesId id, StudentCoursesVO vO) {
        StudentCourses bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        studentCoursesRepository.save(bean);
    }

    public StudentCoursesDTO getById(StudentCoursesId id) {
        StudentCourses original = requireOne(id);
        return toDTO(original);
    }

    public Page<StudentCoursesDTO> query(StudentCoursesVO vO) {
        throw new UnsupportedOperationException();
    }

    public static StudentCoursesDTO toDTO(StudentCourses original) {
        StudentCoursesDTO bean = new StudentCoursesDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private StudentCourses requireOne(StudentCoursesId id) {
        return studentCoursesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
