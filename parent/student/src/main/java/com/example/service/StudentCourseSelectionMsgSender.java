package com.example.service;

import com.example.config.RabbitMQConfig;
import com.example.pojo.StudentCourses;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseSelectionMsgSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void studentCourseSelectionSend(StudentCourses evaluation) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String msg = mapper.writeValueAsString(evaluation);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                msg
        );
        System.out.println("producer: " + msg);
    }
}
