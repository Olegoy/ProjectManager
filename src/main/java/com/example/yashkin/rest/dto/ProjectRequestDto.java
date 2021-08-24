package com.example.yashkin.rest.dto;

import com.example.yashkin.model.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectRequestDto {

    @Schema(description = "Название проекта")
    private String projectName;

    @Schema(description = "Заказчик проекта")
    private UserRequestDto customer;

    @Schema(description = "Статус проекта")
    private ProjectStatus status;

    public ProjectRequestDto(String projectName, UserRequestDto customer) {
        this.projectName = projectName;
        this.customer = customer;
    }

    public ProjectRequestDto(String projectName, UserRequestDto customer, ProjectStatus status) {
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

    public UserRequestDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserRequestDto customer) {
        this.customer = customer;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
