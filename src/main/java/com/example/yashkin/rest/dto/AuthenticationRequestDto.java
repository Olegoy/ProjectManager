package com.example.yashkin.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder()
public class AuthenticationRequestDto {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationRequestDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
