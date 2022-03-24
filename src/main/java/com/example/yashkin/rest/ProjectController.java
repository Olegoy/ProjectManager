package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.service.ProjectService;
import com.example.yashkin.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Проект", description = "CRUD Проекта")
@RestController
@RequestMapping("${project.uri}/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Operation(summary = "Получить список проектов", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        List<ProjectResponseDto> projectResponseDto = projectService.getAllProjects();
        return ResponseEntity.ok().body(projectResponseDto);
    }

    @Operation(summary = "Получить проект по id", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        ProjectResponseDto responseDto = projectService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить проект", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ProjectResponseDto> addProject(@RequestBody ProjectRequestDto requestDto) {
        // добавление в БД
        ProjectResponseDto responseDto = projectService.addProject(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление проекта", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id,
                                                            @RequestBody ProjectRequestDto requestDto) {
        // обновление сущности в БД
        ProjectResponseDto responseDto = projectService.updateProject(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление проекта", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        // удаление сущности из БД
        ProjectResponseDto responseDto = projectService.deleteProject(id);
        return new ResponseEntity<>(
                String.format("Проект с id #%d успешно удален", responseDto.getId()),
                HttpStatus.OK
        );

    }

    @Operation(summary = "Завершение проекта", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}/finish")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> finishProject(@PathVariable Long id) {

        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                String.format("Проект с id #%d не может быть завершен", id),
                HttpStatus.OK
        );

        long count = taskService.unfinishedTasksByProjectId(id).size();
        boolean projectIsFinished = count < 1;
        if (projectIsFinished) {
            ProjectResponseDto projectResponseDto = projectService.setFinishedStatusProject(id);
            responseEntity = new ResponseEntity<>(
                    String.format("Проект с id #%d был успешно завершен", id),
                    HttpStatus.OK
            );
        }
        return responseEntity;
    }

}
