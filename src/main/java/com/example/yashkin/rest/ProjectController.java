package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

public class ProjectController {

    @Operation(summary = "Получить список проектов")
    @GetMapping(value = "/projects")
    public ResponseEntity<List<ProjectResponseDto>> getProjects() {
        ProjectResponseDto project1 = new ProjectResponseDto("project1", "customer1");
        ProjectResponseDto project2 = new ProjectResponseDto("project2", "customer2");

        List<ProjectResponseDto> results =  List.of(project1, project2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Добавить проект")
    @PostMapping(value = "/projects")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto) {
        // добавление в БД

        return ResponseEntity.ok().body(new ProjectResponseDto(requestDto.getName(), requestDto.getCustomer()));
    }

    @Operation(summary = "Обновление проекта")
    @PutMapping(value = "/projects/{id}")
    public ResponseEntity<ProjectResponseDto> partialUpdateProject(@PathVariable Long id,
                                                             @RequestBody ProjectRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        throw new IOException();
        //return ResponseEntity.ok().body(new ProjectResponseDto(requestDto.getName(), requestDto.getId()));
    }

    @Operation(summary = "Удаление проекта")
    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        // удаление сущности из БД

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
