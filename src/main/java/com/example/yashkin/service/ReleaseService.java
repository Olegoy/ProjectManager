package com.example.yashkin.service;

import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;

public interface ReleaseService {

    ReleaseResponseDto getById(Long id);

    ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto);

    ReleaseResponseDto updateRelease(Long id, ReleaseRequestDto releaseRequestDto);

    ReleaseResponseDto deleteRelease(Long id);
}
