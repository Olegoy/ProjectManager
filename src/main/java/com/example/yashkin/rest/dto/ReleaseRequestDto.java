package com.example.yashkin.rest.dto;

import com.example.yashkin.entity.TaskEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "Релиз")
public class ReleaseRequestDto {

    @Schema(description = "ID релиза")
    private Long id;

    @Schema(description = "Версия")
    private Integer version;

    @Schema(description = "Дата начала")
    private LocalDateTime dateStart;

    @Schema(description = "Дата завершения")
    private LocalDateTime dateEnd;

    public ReleaseRequestDto(Long id) {
        this.id = id;
    }

    public ReleaseRequestDto(Long id, Integer version, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.id = id;
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
