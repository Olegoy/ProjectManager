package com.example.yashkin.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Релиз")
public class ReleaseResponseDto {

    @Schema(description = "ID релиза")
    private Long id;

    @Schema(description = "Версия")
    private Integer version;

    @Schema(description = "Дата начала")
    private LocalDateTime dateStart;

    @Schema(description = "Дата завершения")
    private LocalDateTime dateEnd;

    public ReleaseResponseDto() {
    }

    public ReleaseResponseDto(Long id) {
        this.id = id;
    }

    public ReleaseResponseDto(Integer version) {
        this.version = version;
    }

    public ReleaseResponseDto(Integer version, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.version = version;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
}
