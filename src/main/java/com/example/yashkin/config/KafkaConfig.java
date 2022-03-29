package com.example.yashkin.config;

import com.example.yashkin.constants.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(Constants.TOPIC_PROJECTS)
                .partitions(5)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name(Constants.TOPIC_USERS)
                .partitions(5)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic3() {
        return TopicBuilder.name(Constants.TOPIC)
                .partitions(5)
                .replicas(1)
                .build();
    }
}
