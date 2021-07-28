package com.example.yashkin.service;

import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;

public interface TaskService {

    TaskResponseDto getById(Long id);

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);

    TaskResponseDto deleteTask(Long id);

}
