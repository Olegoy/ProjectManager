package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import com.example.yashkin.service.ReleaseService;
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

@Tag(name = "Релиз", description = "CRUD Релиза")
@RestController
@RequestMapping("${project.uri}/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @Operation(summary = "Получить список релизов", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<ReleaseResponseDto>> getReleases() {
        List<ReleaseResponseDto> results = releaseService.getAllRelease();
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Получить релиз по id", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<ReleaseResponseDto> getReleaseById(@PathVariable Long id) {
        ReleaseResponseDto responseDto = releaseService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить релиз", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ReleaseResponseDto> addRelease(@RequestBody ReleaseRequestDto requestDto) {
        // добавление в БД
        ReleaseResponseDto responseDto = releaseService.addRelease(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление релиза", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ReleaseResponseDto> updateRelease(@PathVariable Long id,
                                                            @RequestBody ReleaseRequestDto requestDto) {
        // обновление сущности в БД
        ReleaseResponseDto responseDto = releaseService.updateRelease(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление релиза", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> deleteRelease(@PathVariable Long id) {
        // удаление сущности из БД
        ReleaseResponseDto responseDto = releaseService.deleteRelease(id);
        return new ResponseEntity<>(
                String.format("Релиз с id #%d успешно удален", responseDto.getId()),
                HttpStatus.OK
        );
    }

}
