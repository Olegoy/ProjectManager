package com.example.yashkin.rest.dto;

import com.example.yashkin.model.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectRequestDto {

    @Schema(description = "ID проекта")
    private Long id;

    @Schema(description = "Название проекта")
    private String projectName;

    @Schema(description = "Заказчик проекта")
    private UserRequestDto customer;

    @Schema(description = "Статус проекта")
    private ProjectStatus status;

    public ProjectRequestDto(Long id) {
        this.id = id;
    }

    public ProjectRequestDto(Long id, String projectName, UserRequestDto customer, ProjectStatus status) {
        this.id = id;
        this.projectName = projectName;
        this.customer = customer;
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
