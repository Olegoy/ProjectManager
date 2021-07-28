package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ReleaseEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.ReleaseMapper;
import com.example.yashkin.repository.ReleaseRepository;
import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import com.example.yashkin.service.ReleaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private ReleaseRepository releaseRepository;

    private ReleaseMapper INSTANCE;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseMapper INSTANCE) {
        this.releaseRepository = releaseRepository;
        this.INSTANCE = INSTANCE;
    }

    @Transactional
    @Override
    public ReleaseResponseDto getById(Long id) throws NullPointerException {

        ReleaseEntity releaseEntity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Release with ID = %d not found", id))
        );

        ReleaseResponseDto responseDto = INSTANCE.releaseResponseDtoFromReleaseEntity(releaseEntity);

        return responseDto;

    }

    @Transactional
    @Override
    public ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto) {

        ReleaseEntity entity = INSTANCE.releaseEntityFromReleaseRequestDto(releaseRequestDto);
        releaseRepository.save(entity);

        ReleaseResponseDto responseDto = INSTANCE.releaseResponseDtoFromReleaseEntity(entity);
        return  responseDto;

    }

    @Transactional
    @Override
    public ReleaseResponseDto updateRelease(Long id, ReleaseRequestDto releaseRequestDto) throws NullPointerException {
        ReleaseEntity entity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Release with ID = %d not found", id))
        );
        entity.setVersion(releaseRequestDto.getVersion());
        entity.setDateStart(releaseRequestDto.getDateStart());
        entity.setDateEnd(releaseRequestDto.getDateEnd());
        ReleaseResponseDto responseDto = INSTANCE.releaseResponseDtoFromReleaseEntity(entity);
        return responseDto;
    }

    @Transactional
    @Override
    public ReleaseResponseDto deleteRelease(Long id) throws NullPointerException {
        ReleaseEntity entity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Release with ID = %d not found", id))
        );
        releaseRepository.delete(entity);
        ReleaseResponseDto responseDto = INSTANCE.releaseResponseDtoFromReleaseEntity(entity);
        return responseDto;
    }
}
