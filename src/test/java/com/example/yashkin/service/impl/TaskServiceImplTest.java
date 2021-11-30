package com.example.yashkin.service.impl;

import com.example.yashkin.entity.TaskEntity;
import com.example.yashkin.mappers.TaskMapper;
import com.example.yashkin.repository.ReleaseRepository;
import com.example.yashkin.repository.TaskRepository;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ReleaseRepository releaseRepository;

    private TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    @Before
    public void setUp() throws Exception {
        taskMapper = new TaskMapper() {
            @Override
            public TaskEntity taskEntityFromTaskRequestDto(TaskRequestDto taskRequestDto) {
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setId(taskRequestDto.getId());
                taskEntity.setName(taskRequestDto.getName());
                taskEntity.setAuthor(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getAuthor());
                taskEntity.setExecutor(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getExecutor());
                taskEntity.setType(taskRequestDto.getType());
                taskEntity.setStatus(taskRequestDto.getStatus());
                taskEntity.setPriority(taskRequestDto.getPriority());
                taskEntity.setProjectId(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getProjectId());
                taskEntity.setRelease(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getRelease());
                return taskEntity;
            }

            @Override
            public TaskResponseDto taskResponseDtoFromTaskEntity(TaskEntity taskEntity) {
                TaskResponseDto responseDto = new TaskResponseDto();
                responseDto.setId(taskEntity.getId());
                responseDto.setName(taskEntity.getName());
                responseDto.setAuthor(taskMapper.taskResponseDtoFromTaskEntity(taskEntity).getAuthor());
                responseDto.setExecutor(taskMapper.taskResponseDtoFromTaskEntity(taskEntity).getExecutor());
                responseDto.setType(taskEntity.getType());
                responseDto.setStatus(taskEntity.getStatus());
                responseDto.setPriority(taskEntity.getPriority());
                responseDto.setProjectId(taskMapper.taskResponseDtoFromTaskEntity(taskEntity).getProjectId());
                responseDto.setRelease(taskMapper.taskResponseDtoFromTaskEntity(taskEntity).getRelease());
                return responseDto;
            }

            @Override
            public void updateTaskEntityFromTaskRequestDto(TaskRequestDto taskRequestDto, TaskEntity taskEntity) {

            }

            @Override
            public void updateTaskResponseDtoFromTaskEntity(TaskEntity taskEntity, TaskResponseDto taskResponseDto) {

            }
        };

        taskService = new TaskServiceImpl(taskRepository, userRepository, releaseRepository, taskMapper);
    }

    @Test
    public void findTasksByFilter() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void addTask() {
    }

    @Test
    public void updateTask() {
    }

    @Test
    public void deleteTask() {
    }

    @Test
    public void unfinishedTasksByRelease() {
    }

    @Test
    public void getAllTask() {
    }

    @Test
    public void unfinishedTasksByProjectId() {
    }

    @Test
    public void setStatusTask() {
    }

    @Test
    public void setReleaseTask() {
    }

    @Test
    public void createFromFile() {
    }

    @Test
    public void setExecutorTask() {
    }
}