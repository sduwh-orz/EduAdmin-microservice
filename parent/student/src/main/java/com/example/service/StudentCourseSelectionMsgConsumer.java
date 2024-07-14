package com.example.service;

import com.example.config.RabbitMQConfig;
import com.example.pojo.StudentCourses;
import com.example.repository.StudentCoursesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class StudentCourseSelectionMsgConsumer {
    @Autowired
    private StudentCoursesRepository studentCoursesRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive(Message msg) {
        String msgBody = new String(msg.getBody(), StandardCharsets.UTF_8);
        System.out.println("consumer: " + msgBody);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            StudentCourses studentCourses = objectMapper.readValue(msgBody, StudentCourses.class);
            studentCoursesRepository.save(studentCourses);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
