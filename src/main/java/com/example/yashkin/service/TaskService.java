package com.example.yashkin.service;

import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;

import java.util.UUID;

public interface TaskService {

    TaskResponseDto getById(UUID id);

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(TaskRequestDto taskRequestDto);

    TaskResponseDto deleteTask(UUID id);

}
