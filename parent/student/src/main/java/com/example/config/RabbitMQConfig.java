package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration // 声明这是一个配置类
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "studentCourseSelectionExchange"; // 定义交换机名字
    public static final String QUEUE_NAME = "studentCourseSelectionQueue"; // 定义队列名字
    public static final String ROUTING_KEY = "studentCourseSelectionRoutingKey"; // 定义路由键
    // 创建队列
    @Bean
    public Queue studentCourseSelectionQueue() {
        return new Queue(QUEUE_NAME);
    }
    // 创建交换机
    @Bean
    public DirectExchange studentCourseSelectionExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }
    // 绑定队列和交换机
    @Bean
    public Binding binding(Queue myQueue, DirectExchange myExchange) {
        return BindingBuilder.bind(myQueue).to(myExchange).with(ROUTING_KEY);
    }
}