package com.example.service;

import com.example.dto.StudentInfoDTO;
import com.example.pojo.StudentInfo;
import com.example.repository.StudentInfoRepository;
import com.example.vo.StudentInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentInfoService {

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    public String save(StudentInfoVO vO) {
        StudentInfo bean = new StudentInfo();
        BeanUtils.copyProperties(vO, bean);
        bean = studentInfoRepository.save(bean);
        return bean.getUserId();
    }

    public void delete(String id) {
        studentInfoRepository.deleteById(id);
    }

    public void update(String id, StudentInfoVO vO) {
        StudentInfo bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        studentInfoRepository.save(bean);
    }

    public StudentInfoDTO getById(String id) {
        StudentInfo original = requireOne(id);
        return toDTO(original);
    }

    public Page<StudentInfoDTO> query(StudentInfoVO vO) {
        throw new UnsupportedOperationException();
    }

    public static StudentInfoDTO toDTO(StudentInfo original) {
        StudentInfoDTO bean = new StudentInfoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private StudentInfo requireOne(String id) {
        return studentInfoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
