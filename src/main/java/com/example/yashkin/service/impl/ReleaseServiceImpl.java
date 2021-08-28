package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ReleaseEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.ReleaseMapper;
import com.example.yashkin.repository.ReleaseRepository;
import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import com.example.yashkin.service.ReleaseService;
import com.example.yashkin.utils.Translator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private static final Logger log = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, @Qualifier("releaseMapperImpl") ReleaseMapper INSTANCE) {
        this.releaseRepository = releaseRepository;
        this.releaseMapper = INSTANCE;
    }

    @Transactional
    @Override
    public List<ReleaseResponseDto> getAllRelease() {
        List<ReleaseEntity> allReleaseEntity = releaseRepository.findAll();
        List<ReleaseResponseDto> allRelease = allReleaseEntity.stream()
                .map(releaseMapper::releaseResponseDtoFromReleaseEntity)
                .collect(Collectors.toList());

        log.info("got all release");
        return allRelease;
    }

    @Transactional
    @Override
    public ReleaseResponseDto getById(Long id) {

        ReleaseEntity releaseEntity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(Translator.toLocale("release.exception.not_found_by_id"), id))
        );

        ReleaseResponseDto responseDto = releaseMapper.releaseResponseDtoFromReleaseEntity(releaseEntity);
        log.info("release got by id");
        return responseDto;

    }

    @Transactional
    @Override
    public ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto) {

        ReleaseEntity entity = releaseMapper.releaseEntityFromReleaseRequestDto(releaseRequestDto);
      //  entity.setId(null);
        entity.setDateStart(LocalDateTime.now());
        entity.setDateEnd(null);
        releaseRepository.save(entity);

        ReleaseResponseDto responseDto = releaseMapper.releaseResponseDtoFromReleaseEntity(entity);
        log.info("release added");
        return  responseDto;

    }

    @Transactional
    @Override
    public ReleaseResponseDto updateRelease(Long id, ReleaseRequestDto releaseRequestDto) {
        ReleaseEntity entity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(Translator.toLocale("release.exception.not_found_by_id"), id))
        );
        entity.setVersion(releaseRequestDto.getVersion());
        entity.setDateStart(releaseRequestDto.getDateStart());
        entity.setDateEnd(releaseRequestDto.getDateEnd());
        ReleaseResponseDto responseDto = releaseMapper.releaseResponseDtoFromReleaseEntity(entity);
        log.info("release updated");
        return responseDto;
    }

    @Transactional
    @Override
    public ReleaseResponseDto deleteRelease(Long id) {
        ReleaseEntity entity = releaseRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(Translator.toLocale("release.exception.not_found_by_id"), id))
        );
        releaseRepository.delete(entity);
        ReleaseResponseDto responseDto = releaseMapper.releaseResponseDtoFromReleaseEntity(entity);
        log.info("release deleted");
        return responseDto;
    }
}
