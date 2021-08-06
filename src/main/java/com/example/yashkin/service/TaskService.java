package com.example.yashkin.service;

import com.example.yashkin.model.TaskStatus;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto getById(Long id);

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);

    TaskResponseDto deleteTask(Long id);

    List<TaskResponseDto> unfinishedTasksByRelease(Long releaseId);

    List<TaskResponseDto> getAllTask();

    List<TaskResponseDto> unfinishedTasksByProjectId(Long projectId);

    TaskResponseDto setStatusTask(Long id, TaskStatus status);

    TaskResponseDto setReleaseTask(Long id, Long releaseId);

    TaskResponseDto setExecutorTask(Long id, Long userId);
}
