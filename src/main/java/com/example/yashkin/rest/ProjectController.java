package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.service.ProjectService;
import com.example.yashkin.service.TaskService;
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
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Проект", description = "CRUD Проекта")
@RestController
@RequestMapping("${project.uri}")
public class ProjectController {

    private ProjectService projectService;

    private TaskService taskService;

    public ProjectController() {
    }

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Operation(summary = "Получить список проектов")
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        List<ProjectResponseDto> projectResponseDto = projectService.getAllProjects();
        return ResponseEntity.ok().body(projectResponseDto);
    }

    @Operation(summary = "Получить проект по id")
    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        ProjectResponseDto responseDto = projectService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить проект")
    @PostMapping("/projects")
    public ResponseEntity<ProjectResponseDto> addProject(@RequestBody ProjectRequestDto requestDto) {
        // добавление в БД
        ProjectResponseDto responseDto = projectService.addProject(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление проекта")
    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id,
                                                             @RequestBody ProjectRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        ProjectResponseDto responseDto = projectService.updateProject(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление проекта")
    @DeleteMapping("/projects/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        // удаление сущности из БД
        ProjectResponseDto responseDto = projectService.deleteProject(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Завершение проекта")
    @PutMapping("/projects/{id}/finished")
    public ResponseEntity<String> finishedProject(@PathVariable Long id) throws IOException {

        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                String.format("Проект с id #%d не может быть завершен", id),
                HttpStatus.OK
        );
        long count = 0L;
        for (TaskResponseDto taskResponseDto : taskService.unfinishedTasksByProjectId(id)) {
            count++;
        }
        boolean projectIsFinished = count < 1;
        if(projectIsFinished) {
            projectService.setFinishedStatusProject(id);
            responseEntity = new ResponseEntity<>(
                    String.format("Проект с id #%d был успешно завершен", id),
                    HttpStatus.OK
            );
        }

        return responseEntity;

    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
