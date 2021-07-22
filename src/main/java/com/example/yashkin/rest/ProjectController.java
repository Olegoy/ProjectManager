package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Tag(name = "Проект", description = "CRUD Проекта")
@RestController
@RequestMapping("/api/rest/projects")
public class ProjectController {

    @Operation(summary = "Получить список проектов")
    @GetMapping(value = "/projects")
    public ResponseEntity<List<ProjectResponseDto>> getProjects() {
        ProjectResponseDto project1 = new ProjectResponseDto("project1", "customer1");
        ProjectResponseDto project2 = new ProjectResponseDto("project2", "customer2");

        List<ProjectResponseDto> results =  List.of(project1, project2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Получить проект по id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        ProjectResponseDto project = new ProjectResponseDto(1L);
        return new ResponseEntity<>(project, HttpStatus.OK);
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
