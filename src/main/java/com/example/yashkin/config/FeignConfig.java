package com.example.yashkin.config;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

// Конфигурация для всех feign-клиентов
@Configuration
// Важно прописать эту аннотацию, чтобы запросы feign были разрешены
@EnableFeignClients(basePackages = "com.example.yashkin.feign")
//@EnableOAuth2Client
public class FeignConfig {

    // Добавляет к Feign-клиенту проброс токена на все вызываемые сервисы
    @Bean
    @ConditionalOnProperty(value = "application.security.enabled", havingValue = "true")
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return template -> {
            template.header(HttpHeaders.AUTHORIZATION, "Bearer " + "token");
        };
    }
}
