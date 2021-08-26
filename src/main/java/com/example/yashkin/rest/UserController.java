package com.example.yashkin.rest;

import com.example.yashkin.model.Role;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Tag(name = "Пользователи", description = "CRUD Пользователей")
@RestController
@RequestMapping("${project.uri}/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить список пользователей")
    @GetMapping("/")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> results = userService.getUsers();
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Получить пользователя по id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto responseDto = userService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить пользователя")
    @PostMapping("/")
    @PreAuthorize("hasAuthority('users:write')")
/*    public ResponseEntity<String> addUser(@RequestBody UserRequestDto requestDto) {*/
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) {
        // добавление в БД
        System.out.println("Вызов контроллера add User");

        UserResponseDto responseDto = userService.addUser(requestDto);
/*        String responseDto = userService.addUser(requestDto);*/

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление пользователя")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                             @RequestBody UserRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        UserResponseDto responseDto = userService.updateUser(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // удаление сущности из БД
        UserResponseDto responseDto = userService.deleteUser(id);

        return new ResponseEntity<>(
                String.format("Пользователь с id #%d успешно удален", id),
                HttpStatus.OK
        );
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
