package com.example.yashkin.mappers;

import com.example.yashkin.entity.TaskEntity;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TaskMapper {

    TaskEntity taskEntityFromTaskRequestDto(TaskRequestDto taskRequestDto);

    TaskResponseDto taskResponseDtoFromTaskEntity(TaskEntity taskEntity);

    void updateTaskEntityFromTaskRequestDto(TaskRequestDto taskRequestDto, @MappingTarget TaskEntity taskEntity);

    void updateTaskResponseDtoFromTaskEntity(TaskEntity taskEntity, @MappingTarget TaskResponseDto taskResponseDto);
}
