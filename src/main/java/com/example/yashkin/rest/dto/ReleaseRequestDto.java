package com.example.yashkin.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Релиз")
public class ReleaseRequestDto {

    @Schema(description = "Версия")
    private Integer version;

    public ReleaseRequestDto(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
