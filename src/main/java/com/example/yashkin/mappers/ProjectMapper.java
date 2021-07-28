package com.example.yashkin.mappers;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.service.impl.ProjectServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProjectMapper {

    ProjectEntity projectEntityFromProjectRequestDto(ProjectRequestDto projectRequestDto);

    ProjectResponseDto projectResponseDtoFromProjectEntity(ProjectEntity projectEntity);

    void updateProjectEntityFromProjectRequestDto(ProjectRequestDto projectRequestDto, @MappingTarget ProjectEntity projectEntity);

    void updateProjectResponseDtoFromProjectEntity(ProjectEntity projectEntity, @MappingTarget ProjectResponseDto projectResponseDto);
}
