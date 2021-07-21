package com.example.yashkin.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectRequestDto {

    @Schema(description = "Название проекта")
    private String name;

    @Schema(description = "Заказчик проекта")
    private String customer;

    public ProjectRequestDto(String name, String customer) {
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

}
