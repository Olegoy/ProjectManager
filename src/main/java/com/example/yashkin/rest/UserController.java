package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
import com.example.yashkin.service.impl.Producer;
import com.example.yashkin.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@Tag(name = "Пользователи", description = "CRUD Пользователей")
@RestController
@RequestMapping("${project.uri}/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserService userService;
    @Autowired
    private final Producer producer;

    public UserController(UserService userService, Producer producer) {
        this.userService = userService;
        this.producer = producer;
    }

    @Operation(summary = "Получить список пользователей", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        long startTime = System.currentTimeMillis();
        List<UserResponseDto> results = userService.getUsers();
        producer.sendMessageFromService("got AllUsers" + results.stream()
                .map(s -> (s.getFirstName() + " " + s.getLastName()))
                .collect(Collectors.toSet()));
        long endTime = System.currentTimeMillis() - startTime;
        log.info("Duration = {}", endTime);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Получить пользователя по id", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        long startTime = System.currentTimeMillis();
        UserResponseDto responseDto = userService.getById(id);
        long endTime = System.currentTimeMillis() - startTime;
        log.info("Duration = {}", endTime);
        producer.sendMessageFromService("got user: " + responseDto.getFirstName() + " " + responseDto.getLastName());
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить пользователя", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) {
        // добавление в БД

        UserResponseDto responseDto = userService.addUser(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление пользователя", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @RequestBody UserRequestDto requestDto) {
        // обновление сущности в БД
        UserResponseDto responseDto = userService.updateUser(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление пользователя", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // удаление сущности из БД
        UserResponseDto responseDto = userService.deleteUser(id);
        return new ResponseEntity<>(
                String.format("Пользователь с id #%d успешно удален", responseDto.getId()),
                HttpStatus.OK
        );
    }

}
