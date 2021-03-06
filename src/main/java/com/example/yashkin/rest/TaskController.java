package com.example.yashkin.rest;

import com.example.yashkin.model.TaskStatus;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Задача", description = "CRUD задач")
@RestController
@RequestMapping("${project.uri}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Получить список задач", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> allTasks = taskService.getAllTask();
        return ResponseEntity.ok().body(allTasks);
    }

    @Operation(summary = "Найти задачу по критерию", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/filter/")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<TaskResponseDto>> findTasksByFilter(@Parameter(description = "Название задачи")
                                                                   @RequestParam(required = false) String name,
                                                                   @Parameter(description = "Тип задачи")
                                                                   @RequestParam(required = false) String type,
                                                                   @Parameter(description = "Статус задачи")
                                                                   @RequestParam(required = false) TaskStatus status,
                                                                   @Parameter(description = "Имя проекта")
                                                                   @RequestParam(required = false) String projectName,
                                                                   @Parameter(description = "Версия релиза")
                                                                   @RequestParam(required = false) String releaseVersion,
                                                                   @Parameter(description = "Автор задачи")
                                                                   @RequestParam(required = false) String authorName,
                                                                   @Parameter(description = "Исполнитель задачи")
                                                                   @RequestParam(required = false) String executorName) {

        List<TaskResponseDto> allTasks = taskService.findTasksByFilter(name, type, status, projectName,
                releaseVersion, authorName, executorName);
        return ResponseEntity.ok().body(allTasks);
    }


    @Operation(summary = "Получить количество незавершенных задач на данный релиз", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/unfinished/release")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<Long> getCountUnfinishedTasksByRelease(Long releaseId) {
        Long countUnfinishedTasks = (long) taskService.unfinishedTasksByRelease(releaseId).size();
        return ResponseEntity.ok().body(countUnfinishedTasks);
    }

    @Operation(summary = "Получить количество незавершенных задач проекта", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/unfinished/project")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<Long> getCountUnfinishedTasksByProject(Long projectId) {
        Long countUnfinishedTasks = (long) taskService.unfinishedTasksByProjectId(projectId).size();
        return ResponseEntity.ok().body(countUnfinishedTasks);
    }

    @Operation(summary = "Получить задачу по id", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        TaskResponseDto responseDto = taskService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать задачу из файла CSV", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/csv/")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<List<TaskResponseDto>> createTaskFromCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<TaskResponseDto> responseDto = taskService.createFromFile(file);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить задачу", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskRequestDto requestDto) {
        // добавление в БД
        TaskResponseDto responseDto = taskService.addTask(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление задачи", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id,
                                                      @RequestBody TaskRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.updateTask(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменение статуса задачи", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<TaskResponseDto> setStatusTask(@PathVariable Long id,
                                                         @RequestBody TaskStatus status) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.setStatusTask(id, status);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Установка релиза задачи", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}/release")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<TaskResponseDto> setReleaseTask(@PathVariable Long id,
                                                          @RequestBody Long releaseId) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.setReleaseTask(id, releaseId);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Установка исполнителя задачи", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}/executor")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<TaskResponseDto> setExecutorTask(@PathVariable Long id,
                                                           @RequestBody Long userId) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.setExecutorTask(id, userId);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление задачи", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        // удаление сущности из БД
        TaskResponseDto responseDto = taskService.deleteTask(id);
        return new ResponseEntity<>(
                String.format("Задача с id #%d успешно удалена", id),
                HttpStatus.OK
        );
    }

}
