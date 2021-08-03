package com.example.yashkin.rest;

import com.example.yashkin.model.TaskStatus;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
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
import java.util.List;

@Tag(name = "Задача", description = "CRUD задач")
@RestController
@RequestMapping("${project.uri}")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Получить список задач")
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> allTasks = taskService.getAllTask();
        return ResponseEntity.ok().body(allTasks);
    }

    @Operation(summary = "Получить количество незавершенных задач на данный релиз")
    @GetMapping("/tasks/unfinished/release")
    public ResponseEntity getCountUnfinishedTasksByRelease(Long releaseId) {
        long count = 0L;
        for (TaskResponseDto taskResponseDto : taskService.unfinishedTasksByRelease(releaseId)) {
            count++;
        }
        Long countUnfinishedTasks = count;

        return ResponseEntity.ok().body(countUnfinishedTasks);
    }

    @Operation(summary = "Получить количество незавершенных задач проекта")
    @GetMapping("/tasks/unfinished/project")
    public ResponseEntity getCountUnfinishedTasksByProject(Long projectId) {
        long count = 0L;
        for (TaskResponseDto taskResponseDto : taskService.unfinishedTasksByProjectId(projectId)) {
            count++;
        }
        Long countUnfinishedTasks = count;

        return ResponseEntity.ok().body(countUnfinishedTasks);
    }

    @Operation(summary = "Получить задачу по id")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        TaskResponseDto responseDto = taskService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить задачу")
    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskRequestDto requestDto) {
        // добавление в БД
        TaskResponseDto responseDto = taskService.addTask(requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление задачи")
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id,
                                                             @RequestBody TaskRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.updateTask(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменение статуса задачи")
    @PutMapping("/tasks/{id}/status")
    public ResponseEntity<TaskResponseDto> setStatusTask(@PathVariable Long id,
                                                         @RequestBody TaskStatus status) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.setStatusTask(id, status);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Установка релиза задачи")
    @PutMapping("/tasks/{id}/release")
    public ResponseEntity<TaskResponseDto> setReleaseTask(@PathVariable Long id,
                                                          @RequestBody Long releaseId) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.setReleaseTask(id, releaseId);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Установка исполнителя задачи")
    @PutMapping("/tasks/{id}/executor")
    public ResponseEntity<TaskResponseDto> setExecutorTask(@PathVariable Long id,
                                                           @RequestBody Long userId) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.setExecutorTask(id, userId);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        // удаление сущности из БД
        TaskResponseDto responseDto = taskService.deleteTask(id);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
