package com.example.yashkin.service;

import com.example.yashkin.model.TaskStatus;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Interface with methods for task management
 * @author Oleg Yaskin
 */
public interface TaskService {

    /**
     * Method for getting a task from the database by filter
     * @param name String
     * @param type String
     * @param status TaskStatus
     * @param projectName String
     * @param releaseVersion String
     * @param authorName String
     * @param executorName String
     * @return List<TaskResponseDto>
     */
    public List<TaskResponseDto> findTasksByFilter(
            String name,
            String type,
            TaskStatus status,
            String projectName,
            String releaseVersion,
            String authorName,
            String executorName
    );

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
     *  Method for creating a task from CSV file
     * @param multipartFile MultipartFile
     * @return TaskResponseDto
     */
    TaskResponseDto createFromFile(MultipartFile multipartFile) throws IOException;

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

    /**
     * Method for getting unfinished tasks by release
     * @param releaseId Long
     * @return List<TaskResponseDto>
     */
    List<TaskResponseDto> unfinishedTasksByRelease(Long releaseId);

    /**
     * Method for get all exist tasks from a database
     * @return List<TaskResponseDto>
     */
    List<TaskResponseDto> getAllTask();

    /**
     * Method for getting unfinished tasks by project's ID
     * @param projectId Long
     * @return List<TaskResponseDto>
     */
    List<TaskResponseDto> unfinishedTasksByProjectId(Long projectId);

    /**
     * Method for setting a status to the task
     * @param id Long
     * @param status TaskStatus
     * @return TaskResponseDto
     */
    TaskResponseDto setStatusTask(Long id, TaskStatus status);

    /**
     * Method for setting a release to the task
     * @param id Long
     * @param releaseId Long
     * @return TaskResponseDto
     */
    TaskResponseDto setReleaseTask(Long id, Long releaseId);

    /**
     * Method for setting an executor to the task
     * @param id Long
     * @param userId Long
     * @return TaskResponseDto
     */
    TaskResponseDto setExecutorTask(Long id, Long userId);
}
