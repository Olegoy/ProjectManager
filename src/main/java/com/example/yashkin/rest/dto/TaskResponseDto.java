package com.example.yashkin.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Задача")
public class TaskResponseDto {

    @Schema(description = "ID проекта")
    private Long projectId;

    @Schema(description = "ID задачи")
    private Long id;

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Автор задачи")
    private String author;

    @Schema(description = "Исполнитель задачи")
    private String executor;

    @Schema(description = "Тип задачи")
    private String type;

    @Schema(description = "Статус задачи")
    private String status;

    @Schema(description = "Приоритет задачи")
    private Long priority;

    @Schema(description = "Версия")
    private Integer version;

    @Schema(description = "Дата начала задачи")
    private LocalDateTime dateStart;

    @Schema(description = "Дата завершения задачи")
    private LocalDateTime dateEnd;

    public TaskResponseDto() {
    }

    public TaskResponseDto(Long id) {
        this.id = id;
    }

    public TaskResponseDto(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
}
