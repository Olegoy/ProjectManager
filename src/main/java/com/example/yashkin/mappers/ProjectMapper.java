package com.example.yashkin.mappers;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectEntity projectEntityFromProjectRequestDto(ProjectRequestDto projectRequestDto);

    ProjectResponseDto projectResponseDtoFromProjectEntity(ProjectEntity projectEntity);

    void updateProjectEntityFromProjectRequestDto(ProjectRequestDto projectRequestDto, @MappingTarget ProjectEntity projectEntity);

    void updateProjectResponseDtoFromProjectEntity(ProjectEntity projectEntity, @MappingTarget ProjectResponseDto projectResponseDto);
}
