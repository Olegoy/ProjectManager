package com.example.yashkin.rest.dto;

import com.example.yashkin.model.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectRequestDto {

    @JsonIgnore
    @Schema(description = "ID проекта")
    private Long id;

    @Schema(description = "Название проекта")
    private String projectName;

    @Schema(description = "Заказчик проекта")
    private Long customerId;

    @Schema(description = "Статус проекта")
    private ProjectStatus status;

    public ProjectRequestDto(Long id) {
        this.id = id;
    }

    public ProjectRequestDto() {
    }

    public ProjectRequestDto(Long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    public ProjectRequestDto(Long id, String projectName, Long customerId, ProjectStatus status) {
        this.id = id;
        this.projectName = projectName;
        this.customerId = customerId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
