package com.example.yashkin.rest.dto;

import com.example.yashkin.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Задача")
public class TaskResponseDto {

    @Schema(description = "ID задачи")
    private Long id;

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Автор задачи")
    private UserResponseDto author;

    @Schema(description = "Исполнитель задачи")
    private UserResponseDto executor;

    @Schema(description = "Тип задачи")
    private String type;

    @Schema(description = "Статус задачи")
    private TaskStatus status;

    @Schema(description = "Приоритет задачи")
    private Long priority;

    @Schema(description = "Проект, к которому относится задача")
    private ProjectResponseDto projectId;

    @Schema(description = "Релиз")
    private ReleaseResponseDto release;


    public TaskResponseDto() {
    }

    public TaskResponseDto(Long id) {
        this.id = id;
    }

    public TaskResponseDto(Long id, String name, UserResponseDto author, UserResponseDto executor, String type, TaskStatus status, Long priority, ProjectResponseDto projectId, ReleaseResponseDto release) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.release = release;
    }

    public TaskResponseDto(Long id, String name, UserResponseDto author, String type, TaskStatus status, Long priority, ProjectResponseDto projectId, ReleaseResponseDto release) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserResponseDto getAuthor() {
        return author;
    }

    public void setAuthor(UserResponseDto author) {
        this.author = author;
    }

    public UserResponseDto getExecutor() {
        return executor;
    }

    public void setExecutor(UserResponseDto executor) {
        this.executor = executor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public ProjectResponseDto getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectResponseDto projectId) {
        this.projectId = projectId;
    }

    public ReleaseResponseDto getRelease() {
        return release;
    }

    public void setRelease(ReleaseResponseDto release) {
        this.release = release;
    }
}
