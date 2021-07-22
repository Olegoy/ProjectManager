package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Релиз", description = "CRUD Релиза")
@RestController
@RequestMapping("/api/rest/releases")
public class ReleaseController {

    @Operation(summary = "Получить список релизов")
    @GetMapping(value = "/releases")
    public ResponseEntity<List<ReleaseResponseDto>> getReleases() {
        ReleaseResponseDto release = new ReleaseResponseDto(1);
        ReleaseResponseDto release2 = new ReleaseResponseDto(2);

        List<ReleaseResponseDto> results =  List.of(release, release2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Получить релиз по id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReleaseResponseDto> getReleaseById(@PathVariable Long id) {
        ReleaseResponseDto release = new ReleaseResponseDto(1L);
        return new ResponseEntity<>(release, HttpStatus.OK);
    }

    @Operation(summary = "Добавить релиз")
    @PostMapping(value = "/releases")
    public ResponseEntity<ReleaseResponseDto> createRelease(@RequestBody ReleaseRequestDto requestDto) {
        // добавление в БД

        return ResponseEntity.ok().body(new ReleaseResponseDto(requestDto.getVersion(), requestDto.getDateStart(), requestDto.getDateEnd()));
    }

    @Operation(summary = "Обновление релиза")
    @PutMapping(value = "/releases/{version}")
    public ResponseEntity<ReleaseResponseDto> partialUpdateRelease(@PathVariable int version,
                                                             @RequestBody ReleaseRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        throw new IOException();
        //return ResponseEntity.ok().body(new UserResponseDto(requestDto.getFirstName(), requestDto.getSecondName()));
    }

    @Operation(summary = "Удаление релиза")
    @DeleteMapping(value = "/releases/{version}")
    public ResponseEntity deleteRelease(@PathVariable int version) {
        // удаление сущности из БД

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
