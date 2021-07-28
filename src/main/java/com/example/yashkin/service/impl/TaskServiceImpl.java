package com.example.yashkin.service.impl;

import com.example.yashkin.entity.TaskEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.TaskMapper;
import com.example.yashkin.repository.TaskRepository;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private TaskMapper INSTANCE;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper INSTANCE) {
        this.taskRepository = taskRepository;
        this.INSTANCE = INSTANCE;
    }

    @Transactional
    @Override
    public TaskResponseDto getById(Long id) throws NullPointerException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );

        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(taskEntity);

        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {

        TaskEntity entity = INSTANCE.taskEntityFromTaskRequestDto(taskRequestDto);
        taskRepository.save(entity);

        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) throws NullPointerException {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        entity.setName(taskRequestDto.getName());
        entity.setAuthor(taskRequestDto.getAuthor());
        entity.setExecutor(taskRequestDto.getExecutor());
        entity.setStatus(taskRequestDto.getStatus());
        entity.setPriority(taskRequestDto.getPriority());
        entity.setType(taskRequestDto.getType());
        entity.setVersion(taskRequestDto.getVersion());
        entity.setDateStart(taskRequestDto.getDateStart());
        entity.setDateEnd(taskRequestDto.getDateEnd());
        TaskResponseDto responseDto = INSTANCE.taskResponseDtoFromTaskEntity(entity);
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
        return responseDto;
    }
}
