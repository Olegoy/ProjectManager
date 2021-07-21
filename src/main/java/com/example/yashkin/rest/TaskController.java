package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

public class TaskController {

    @Operation(summary = "Получить список задач")
    @GetMapping(value = "/tasks")
    public ResponseEntity<List<TaskResponseDto>> getTasks() {
        TaskResponseDto task = new TaskResponseDto("task1", "author1");
        TaskResponseDto task2 = new TaskResponseDto("task2", "author2");

        List<TaskResponseDto> results =  List.of(task, task2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Добавить задачу")
    @PostMapping(value = "/tasks")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto requestDto) {
        // добавление в БД

        return ResponseEntity.ok().body(new TaskResponseDto(requestDto.getName(), requestDto.getAuthor()));
    }

    @Operation(summary = "Обновление задачи")
    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<TaskResponseDto> partialUpdateTask(@PathVariable Long id,
                                                             @RequestBody TaskRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        throw new IOException();
        //return ResponseEntity.ok().body(new TaskResponseDto(requestDto.getName(), requestDto.getAuthor()));
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        // удаление сущности из БД

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
