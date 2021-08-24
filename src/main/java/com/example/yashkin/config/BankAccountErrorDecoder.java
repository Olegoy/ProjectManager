package com.example.yashkin.config;

import com.example.yashkin.config.model.ErrorMessage;
import com.example.yashkin.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Primary
@Component
public class BankAccountErrorDecoder implements ErrorDecoder {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        ErrorMessage errorMessage;
        try {
            errorMessage = objectMapper.readValue(response.body().asReader(Charset.defaultCharset()), ErrorMessage.class);
        } catch (IOException e) {
            return new IOException(e);
        }
        int status = response.status();

        if (status == 404) {
            return new NotFoundException(errorMessage.getMessage());
        } else if (status >= 400 && status <= 599) {
            //return new BadRequestException(errorMessage);
        }

        return new RuntimeException();
    }
}
