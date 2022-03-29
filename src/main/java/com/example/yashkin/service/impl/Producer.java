package com.example.yashkin.service.impl;

import com.example.yashkin.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.kafkaTemplate.send(Constants.TOPIC, message + "\n This message from " + userDetails.getUsername());
    }

    public void sendMessageFromService(String message) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.kafkaTemplate.send(Constants.TOPIC_USERS, userDetails.getUsername() + " " + message);
    }
}
