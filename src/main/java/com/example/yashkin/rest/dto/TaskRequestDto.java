package com.example.yashkin.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача")
public class TaskRequestDto {

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Автор задачи")
    private String author;

    public TaskRequestDto(String name, String author) {
        this.name = name;
        this.author = author;
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

}
