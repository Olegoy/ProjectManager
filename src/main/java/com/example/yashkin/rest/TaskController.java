package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.UUID;

@Tag(name = "Задача", description = "CRUD задач")
@RestController
@RequestMapping("/api/rest/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Получить список задач")
    @GetMapping(value = "/tasks")
    public ResponseEntity<List<TaskResponseDto>> getTasks() {
        TaskResponseDto task = new TaskResponseDto("task1", "author1");
        TaskResponseDto task2 = new TaskResponseDto("task2", "author2");

        List<TaskResponseDto> results = new ArrayList<>();
        results.add(task);
        results.add(task2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Получить задачу по id")
    @GetMapping(value = "/tasks/get")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID id) {
        TaskResponseDto responseDto = taskService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить задачу")
    @PostMapping(value = "/tasks/add")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskRequestDto requestDto) {
        // добавление в БД
        TaskResponseDto responseDto = taskService.addTask(requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление задачи")
    @PutMapping(value = "/tasks/update")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable UUID id,
                                                             @RequestBody TaskRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        TaskResponseDto responseDto = taskService.updateTask(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping(value = "/tasks/delete")
    public ResponseEntity deleteTask(@PathVariable UUID id) {
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
