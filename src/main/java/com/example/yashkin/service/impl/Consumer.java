package com.example.yashkin.service.impl;

import com.example.yashkin.constants.Constants;
import com.example.yashkin.entity.PayingEntity;
import com.example.yashkin.repository.PayingRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final PayingRepository payingRepository;

    public Consumer(PayingRepository payingRepository) {
        this.payingRepository = payingRepository;
    }

    @KafkaListener(topics = {Constants.TOPIC, Constants.TOPIC_USERS}, groupId = "group_id")
    public void consumeUsers(String message) {
        System.out.println("Consumed message = " + message);
    }

    @KafkaListener(topics = {Constants.TOPIC_PROJECTS}, clientIdPrefix = "ProjectManager", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Its paying project " + message);
        PayingEntity payingEntity = new PayingEntity(message);
        payingRepository.save(payingEntity);
    }
}
