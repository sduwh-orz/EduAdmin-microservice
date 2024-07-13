package com.example.service;

import com.example.pojo.ExamApplication;
import com.example.repository.ExamRepository;
import com.example.repository.FinalExamRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ExamApplicationService {
//    @Autowired
//    private ExamApplicationRepository ExamApplicationRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private FinalExamRepository finalExamRepository;

    public void applyExamByMajorCourseAndFormat(
            String majorId,
            String courseId,
            String examFormat) {

        int finalExamCount = finalExamRepository.countFinalExams();
        int examApplicationCount = Math.toIntExact(stringRedisTemplate.opsForSet().size("finalExam"));

        String examId = String.valueOf(6000 + finalExamCount + examApplicationCount);
        String redisKey = "finalExam_" + examId;

        ExamApplication EA1 = new ExamApplication();
        EA1.setExamId(examId);
        EA1.setMajorId(majorId);
        EA1.setCourseId(courseId);
        EA1.setExamFormat(examFormat);

        ObjectMapper mapper = new ObjectMapper();
        stringRedisTemplate.opsForSet().add("finalExam", redisKey);
        try {
            stringRedisTemplate.opsForValue().set(redisKey, mapper.writeValueAsString(EA1));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ExamApplication> listAllApplication() {
        List<ExamApplication> result = new LinkedList<>();
        Cursor<String> cursor = stringRedisTemplate.opsForSet().scan("finalExam", ScanOptions.NONE);
        ObjectMapper mapper = new ObjectMapper();
        try {
            while (cursor.hasNext()) {
                ExamApplication app = mapper.readValue(
                        stringRedisTemplate.opsForValue().get(cursor.next()),
                        ExamApplication.class
                );
                result.add(app);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cursor.close();
            } catch (Exception e) {
            }
        }
        return result;
    }
}
