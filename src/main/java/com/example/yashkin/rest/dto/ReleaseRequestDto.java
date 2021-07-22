package com.example.yashkin.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Релиз")
public class ReleaseRequestDto {

    @Schema(description = "Версия")
    private Integer version;

    @Schema(description = "Дата начала")
    private LocalDateTime dateStart;

    @Schema(description = "Дата завершения")
    private LocalDateTime dateEnd;

    public ReleaseRequestDto(Integer version) {
        this.version = version;
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
