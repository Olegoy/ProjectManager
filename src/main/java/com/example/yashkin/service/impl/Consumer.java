package com.example.yashkin.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private static final String TOPIC = "user_message";
    private static final String TOPIC_USERS = "user_message";

    @KafkaListener(topics = {TOPIC, TOPIC_USERS}, groupId = "group_id")
    public void consume(String message) throws IOException {
        System.out.println("Consumed message = " + message);

    }
}
