package com.example.yashkin.rest.dto;

import com.example.yashkin.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача")
public class TaskRequestDto {

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Автор задачи")
    private UserRequestDto author;

    @Schema(description = "Исполнитель задачи")
    private UserRequestDto executor;

    @Schema(description = "Тип задачи")
    private String type;

    @Schema(description = "Статус задачи")
    private TaskStatus status;

    @Schema(description = "Приоритет задачи")
    private Integer priority;

    @Schema(description = "Релиз задачи")
    private ReleaseRequestDto release;

    @Schema(description = "Проект, к которому относится задача")
    private ReleaseRequestDto projectId;

    public TaskRequestDto(String name, UserRequestDto author) {
        this.name = name;
        this.author = author;
    }

    public TaskRequestDto(String name, UserRequestDto author, UserRequestDto executor, String type, TaskStatus status, Integer priority, ReleaseRequestDto release, ReleaseRequestDto projectId) {
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.release = release;
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRequestDto getAuthor() {
        return author;
    }

    public void setAuthor(UserRequestDto author) {
        this.author = author;
    }

    public UserRequestDto getExecutor() {
        return executor;
    }

    public void setExecutor(UserRequestDto executor) {
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public ReleaseRequestDto getRelease() {
        return release;
    }

    public void setRelease(ReleaseRequestDto release) {
        this.release = release;
    }

    public ReleaseRequestDto getProjectId() {
        return projectId;
    }

    public void setProjectId(ReleaseRequestDto projectId) {
        this.projectId = projectId;
    }
}
