package com.example.yashkin.mappers;

import com.example.yashkin.entity.TaskEntity;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskEntity taskEntityFromTaskRequestDto(TaskRequestDto taskRequestDto);

    TaskResponseDto taskResponseDtoFromTaskEntity(TaskEntity taskEntity);

    void updateTaskEntityFromTaskRequestDto(TaskRequestDto taskRequestDto, @MappingTarget TaskEntity taskEntity);

    void updateTaskResponseDtoFromTaskEntity(TaskEntity taskEntity, @MappingTarget TaskResponseDto taskResponseDto);
}
