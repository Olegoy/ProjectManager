package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ReleaseEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.ReleaseMapper;
import com.example.yashkin.repository.ReleaseRepository;
import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import com.example.yashkin.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Transactional
    @Override
    public ReleaseResponseDto getById(UUID id) throws NullPointerException {

        ReleaseEntity releaseEntity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Release with ID = ' ' not found")
        );

        ReleaseResponseDto responseDto = ReleaseMapper.INSTANCE.releaseResponseDtoFromReleaseEntity(releaseEntity);

        return responseDto;

    }

    @Transactional
    @Override
    public ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto) {

        ReleaseEntity entity = ReleaseMapper.INSTANCE.releaseEntityFromReleaseRequestDto(releaseRequestDto);
        releaseRepository.save(entity);

        ReleaseResponseDto responseDto = ReleaseMapper.INSTANCE.releaseResponseDtoFromReleaseEntity(entity);
        return  responseDto;

    }

    @Transactional
    @Override
    public ReleaseResponseDto updateRelease(Integer version, ReleaseRequestDto releaseRequestDto) throws NullPointerException {
        ReleaseEntity entity = releaseRepository.findByName(releaseRequestDto.getVersion()).orElseThrow(
                () -> new NotFoundException("Release with ID = ' ' not found")
        );
        ReleaseEntity entity1 = ReleaseMapper.INSTANCE.releaseEntityFromReleaseRequestDto(releaseRequestDto);
        releaseRepository.save(entity1);
        ReleaseResponseDto responseDto = ReleaseMapper.INSTANCE.releaseResponseDtoFromReleaseEntity(entity1);
        return responseDto;
    }

    @Transactional
    @Override
    public ReleaseResponseDto deleteRelease(UUID id) throws NullPointerException {
        ReleaseEntity entity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Release with ID = ' ' not found")
        );
        releaseRepository.delete(entity);
        ReleaseResponseDto responseDto = ReleaseMapper.INSTANCE.releaseResponseDtoFromReleaseEntity(entity);
        return responseDto;
    }
}
