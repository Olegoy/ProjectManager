package com.example.yashkin.rest.dto;

import com.example.yashkin.model.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Проект")
public class ProjectResponseDto {

    @Schema(description = "Название проекта")
    private String name;

    @Schema(description = "Заказчик проекта")
    private String customer;

    @Schema(description = "ID проекта")
    private UUID id;

    @Schema(description = "Статус проекта")
    private ProjectStatus status;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(UUID id) {
        this.id = id;
    }

    public ProjectResponseDto(String name, String customer) {
        this.name = name;
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
