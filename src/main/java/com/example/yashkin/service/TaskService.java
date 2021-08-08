package com.example.yashkin.service;

import com.example.yashkin.model.TaskStatus;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;

import java.util.List;

/**
 * Interface with methods for task management
 * @author Oleg Yaskin
 */
public interface TaskService {

    /**
     * Method for getting a task from the database by ID
     * @param id Long
     * @return TaskResponseDto
     */
    TaskResponseDto getById(Long id);

    /**
     * Method for adding a task to the database
     * @param taskRequestDto TaskRequestDto
     * @return TaskResponseDto
     */
    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    /**
     * Method for updating the task
     * @param id Long
     * @param taskRequestDto TaskRequestDto
     * @return TaskResponseDto
     */
    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);

    /**
     * Method for deleting the task
     * @param id Long
     * @return TaskResponseDto
     */
    TaskResponseDto deleteTask(Long id);

    List<TaskResponseDto> unfinishedTasksByRelease(Long releaseId);

    /**
     * Method for get all exist tasks from a database
     * @return List<TaskResponseDto>
     */
    List<TaskResponseDto> getAllTask();

    List<TaskResponseDto> unfinishedTasksByProjectId(Long projectId);

    TaskResponseDto setStatusTask(Long id, TaskStatus status);

    TaskResponseDto setReleaseTask(Long id, Long releaseId);

    TaskResponseDto setExecutorTask(Long id, Long userId);
}
