package com.example.yashkin.service.impl;

import com.example.yashkin.entity.TaskEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.TaskMapper;
import com.example.yashkin.model.TaskStatus;
import com.example.yashkin.repository.ReleaseRepository;
import com.example.yashkin.repository.TaskRepository;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.service.TaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskServiceImpl implements TaskService {

    private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ReleaseRepository releaseRepository;

    private final TaskMapper INSTANCE;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, ReleaseRepository releaseRepository, @Qualifier("taskMapperImpl") TaskMapper INSTANCE) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.releaseRepository = releaseRepository;
        this.INSTANCE = INSTANCE;
    }

    @Transactional
    @Override
    public TaskResponseDto getById(Long id) throws NullPointerException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );

        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(taskEntity);
        log.info("task got by id");
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {

        TaskEntity entity = INSTANCE.taskEntityFromTaskRequestDto(taskRequestDto);
        taskRepository.save(entity);

        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
        log.info("task added");
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) throws NullPointerException {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        entity.setName(taskRequestDto.getName());
        entity.setAuthor(INSTANCE.taskEntityFromTaskRequestDto(taskRequestDto).getAuthor());
        entity.setExecutor(INSTANCE.taskEntityFromTaskRequestDto(taskRequestDto).getExecutor());
        entity.setStatus(taskRequestDto.getStatus());
        entity.setPriority(taskRequestDto.getPriority());
        entity.setType(taskRequestDto.getType());
        entity.setRelease(INSTANCE.taskEntityFromTaskRequestDto(taskRequestDto).getRelease());
        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
        log.info("task updated");
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto deleteTask(Long id) throws NullPointerException {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        taskRepository.delete(entity);
        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
        log.info("task deleted");
        return responseDto;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> unfinishedTasksByRelease(Long releaseId) {
        List<TaskEntity> tasksByRelease = taskRepository.findAllByRelease(releaseId);
        List<TaskResponseDto> unfinishedTasks = new ArrayList<>();
        for(TaskEntity taskEntity: tasksByRelease) {
            if(!taskEntity.getStatus().toString().equals("DONE")) {
                TaskResponseDto taskResponseDto = INSTANCE.taskResponseDtoFromTaskEntity(taskEntity);
                unfinishedTasks.add(taskResponseDto);
            }
        }
        log.info("got unfinished tasks by release");
        return unfinishedTasks;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> getAllTask() {
        List<TaskEntity> allTasksEntity = taskRepository.findAll();
        List<TaskResponseDto> allTasks = new ArrayList<>();
        for(TaskEntity taskEntity: allTasksEntity) {
            allTasks.add(INSTANCE.taskResponseDtoFromTaskEntity(taskEntity));
        }
        log.info("got all tasks");
        return allTasks;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> unfinishedTasksByProjectId(Long projectId) {
        List<TaskEntity> tasksByProject = taskRepository.findAllByProjectId(projectId);
        List<TaskResponseDto> unfinishedTasks = new ArrayList<>();
        for(TaskEntity taskEntity: tasksByProject) {
            if(!taskEntity.getStatus().toString().equals("DONE")) {
                TaskResponseDto taskResponseDto = INSTANCE.taskResponseDtoFromTaskEntity(taskEntity);
                unfinishedTasks.add(taskResponseDto);
            }
        }
        log.info("got unfinished tasks by project");
        return unfinishedTasks;
    }

    @Transactional
    @Override
    public TaskResponseDto setStatusTask(Long id, TaskStatus status) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );

        entity.setStatus(status);

        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
        log.info("changed task's status");
        return responseDto;
    }

    @Transactional
    @Override
    public TaskResponseDto setReleaseTask(Long id, Long releaseId) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        entity.setRelease(releaseRepository.getById(releaseId));
        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
        log.info("changed task's release");
        return responseDto;
    }

    @Transactional
    @Override
    public TaskResponseDto setExecutorTask(Long id, Long userId) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        entity.setExecutor(userRepository.getById(userId));
        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
        log.info("changed task's executor");
        return responseDto;
    }
}
