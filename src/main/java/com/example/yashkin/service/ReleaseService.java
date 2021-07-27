package com.example.yashkin.service;

import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;

import java.util.UUID;

public interface ReleaseService {

    ReleaseResponseDto getById(UUID id);

    ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto);

    ReleaseResponseDto updateRelease(Integer version, ReleaseRequestDto releaseRequestDto);

    ReleaseResponseDto deleteRelease(UUID id);
}
