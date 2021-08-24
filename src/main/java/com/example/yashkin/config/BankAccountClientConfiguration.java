package com.example.yashkin.config;


import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class BankAccountClientConfiguration {

    @Primary
    @Bean
    public ErrorDecoder bankAccountErrorDecoder() {
        return new BankAccountErrorDecoder();
    }
}
