package com.example.yashkin.rest.dto;

import com.example.yashkin.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача")
public class TaskRequestDto {

    @Schema(description = "ID задачи")
    private Long id;

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

    @Schema(description = "Проект, к которому относится задача")
    private ProjectRequestDto projectId;

    @Schema(description = "Релиз задачи")
    private ReleaseRequestDto release;

    public TaskRequestDto() {
    }

    public TaskRequestDto(Long id, String name, UserRequestDto author, UserRequestDto executor, String type, TaskStatus status, Integer priority, ProjectRequestDto projectId, ReleaseRequestDto release) {
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

    public ProjectRequestDto getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectRequestDto projectId) {
        this.projectId = projectId;
    }

    public ReleaseRequestDto getRelease() {
        return release;
    }

    public void setRelease(ReleaseRequestDto release) {
        this.release = release;
    }
}
