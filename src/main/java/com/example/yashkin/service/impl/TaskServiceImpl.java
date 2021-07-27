package com.example.yashkin.service.impl;

import com.example.yashkin.entity.TaskEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.TaskMapper;
import com.example.yashkin.repository.TaskRepository;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    @Override
    public TaskResponseDto getById(UUID id) throws NullPointerException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Task with ID = ' ' not found")
        );

        TaskResponseDto responseDto = TaskMapper.INSTANCE.taskResponseDtoFromTaskEntity(taskEntity);

        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {

        TaskEntity entity = new TaskEntity("Task1", "Author");
        taskRepository.save(entity);

        TaskResponseDto responseDto = TaskMapper.INSTANCE.taskResponseDtoFromTaskEntity(entity);
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto updateTask(TaskRequestDto taskRequestDto) throws NullPointerException {
        TaskEntity entity = taskRepository.findByName(taskRequestDto.getName()).orElseThrow(
                () -> new NotFoundException("Task with ID = ' ' not found")
        );
        TaskEntity entity1 = TaskMapper.INSTANCE.taskEntityFromTaskRequestDto(taskRequestDto);
        taskRepository.save(entity1);
        TaskResponseDto responseDto = TaskMapper.INSTANCE.taskResponseDtoFromTaskEntity(entity1);
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto deleteTask(UUID id) throws NullPointerException {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Task with ID = ' ' not found")
        );
        taskRepository.delete(entity);
        TaskResponseDto responseDto = TaskMapper.INSTANCE.taskResponseDtoFromTaskEntity(entity);
        return responseDto;
    }
}
