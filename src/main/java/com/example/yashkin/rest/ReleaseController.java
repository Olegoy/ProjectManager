package com.example.yashkin.rest;

import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import com.example.yashkin.service.ReleaseService;
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
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Релиз", description = "CRUD Релиза")
@RestController
@RequestMapping("${project.uri}")
public class ReleaseController {

    private ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @Operation(summary = "Получить список релизов")
    @GetMapping("/releases")
    public ResponseEntity<List<ReleaseResponseDto>> getReleases() {
        ReleaseResponseDto release = new ReleaseResponseDto(1);
        ReleaseResponseDto release2 = new ReleaseResponseDto(2);

        List<ReleaseResponseDto> results = new ArrayList<>();
        results.add(release);
        results.add(release2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Получить релиз по id")
    @GetMapping("/releases/{id}")
    public ResponseEntity<ReleaseResponseDto> getReleaseById(@PathVariable Long id) {
        ReleaseResponseDto responseDto = releaseService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Добавить релиз")
    @PostMapping("/releases/")
    public ResponseEntity<ReleaseResponseDto> addRelease(@RequestBody ReleaseRequestDto requestDto) {
        // добавление в БД
        ReleaseResponseDto responseDto = releaseService.addRelease(requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Обновление релиза")
    @PutMapping("/releases/{id}")
    public ResponseEntity<ReleaseResponseDto> updateRelease(@PathVariable Long id,
                                                             @RequestBody ReleaseRequestDto requestDto) throws IOException {
        // обновление сущности в БД
        ReleaseResponseDto responseDto = releaseService.updateRelease(id, requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удаление релиза")
    @DeleteMapping("/releases/{id}")
    public ResponseEntity deleteRelease(@PathVariable Long id) {
        // удаление сущности из БД
        ReleaseResponseDto responseDto = releaseService.deleteRelease(id);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException e) {
        //
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
