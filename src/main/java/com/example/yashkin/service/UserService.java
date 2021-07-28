package com.example.yashkin.service;

import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;

import java.util.UUID;

public interface UserService {

    UserResponseDto getById(Long id);

    UserResponseDto addUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    UserResponseDto deleteUser(Long id);

}
