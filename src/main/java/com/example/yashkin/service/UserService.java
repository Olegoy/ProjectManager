package com.example.yashkin.service;

import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;

import java.util.UUID;

public interface UserService {

    UserResponseDto getById(UUID id);

    UserResponseDto addUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto);

    UserResponseDto deleteUser(UUID id);

}
