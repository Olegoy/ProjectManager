package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

public class UserController {

    @Operation(summary = "Получить список пользователей")
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        UserResponseDto user = new UserResponseDto("firstName1", "secondName1");
        UserResponseDto user2 = new UserResponseDto("firstName2", "secondName2");

        List<UserResponseDto> results =  List.of(user, user2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Добавить пользователя")
    @PostMapping(value = "/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        // добавление в БД

        return ResponseEntity.ok().body(new UserResponseDto(requestDto.getFirstName(), requestDto.getSecondName()));
    }

    @Operation(summary = "Обновление пользователя")
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<TaskResponseDto> partialUpdateUser(@PathVariable Long id,
                                                             @RequestBody UserRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        throw new IOException();
        //return ResponseEntity.ok().body(new UserResponseDto(requestDto.getFirstName(), requestDto.getSecondName()));
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        // удаление сущности из БД

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
