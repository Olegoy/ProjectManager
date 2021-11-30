package com.example.yashkin.rest.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
