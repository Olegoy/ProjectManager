package com.example.yashkin.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Релиз")
public class ReleaseResponseDto {

    @Schema(description = "Версия")
    private Integer version;

    @Schema(description = "Дата начала")
    private String dateStart;

    @Schema(description = "Дата завершения")
    private String dateEnd;

    public ReleaseResponseDto() {
    }

    public ReleaseResponseDto(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
