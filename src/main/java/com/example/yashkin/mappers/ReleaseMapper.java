package com.example.yashkin.mappers;

import com.example.yashkin.entity.ReleaseEntity;
import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReleaseMapper {

    ReleaseMapper INSTANCE = Mappers.getMapper(ReleaseMapper.class);

    ReleaseEntity releaseEntityFromReleaseRequestDto(ReleaseRequestDto releaseRequestDto);

    ReleaseResponseDto releaseResponseDtoFromReleaseEntity(ReleaseEntity releaseEntity);

    void updateReleaseEntityFromReleaseRequestDto(ReleaseRequestDto releaseRequestDto, @MappingTarget ReleaseEntity releaseEntity);

    void updateReleaseResponseDtoFromReleaseEntity(ReleaseEntity releaseEntity, @MappingTarget ReleaseResponseDto releaseResponseDto);
}
