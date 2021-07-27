package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
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

@Tag(name = "Пользователи", description = "CRUD Пользователей")
@RestController
@RequestMapping("/api/rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Получить список пользователей")
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        UserResponseDto user = new UserResponseDto("firstName1", "secondName1");
        UserResponseDto user2 = new UserResponseDto("firstName2", "secondName2");

        List<UserResponseDto> results =  new ArrayList<>();
        results.add(user);
        results.add(user2);
        return ResponseEntity.ok().body(results);
    }

        @Operation(summary = "Получить пользователя по id")
    @GetMapping(value = "/users/get")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        UserResponseDto responseDto = userService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить пользователя")
    @PostMapping(value = "/users/add")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) {
        // добавление в БД
        UserResponseDto responseDto = userService.addUser(requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление пользователя")
    @PutMapping(value = "/users/update")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id,
                                                             @RequestBody UserRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        UserResponseDto responseDto = userService.updateUser(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping(value = "/users/delete/")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        // удаление сущности из БД
        UserResponseDto responseDto = userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
