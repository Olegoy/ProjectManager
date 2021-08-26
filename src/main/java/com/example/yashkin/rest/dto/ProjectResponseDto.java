package com.example.yashkin.rest.dto;

import com.example.yashkin.model.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectResponseDto {

    @Schema(description = "ID проекта")
    private Long id;

    @Schema(description = "Название проекта")
    private String projectName;

    @Schema(description = "Заказчик проекта")
    private UserResponseDto customer;

    @Schema(description = "Статус проекта")
    private ProjectStatus status;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(Long id, String projectName, UserResponseDto customer, ProjectStatus status) {
        this.id = id;
        this.projectName = projectName;
        this.customer = customer;
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UserResponseDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserResponseDto customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
