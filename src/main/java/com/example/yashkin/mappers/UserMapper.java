package com.example.yashkin.mappers;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserEntity userEntityFromUserRequestDto(UserRequestDto userRequestDto);

    UserResponseDto userResponseDtoFromUserEntity(UserEntity userEntity);

    void updateUserEntityFromUserRequestDto(UserRequestDto userRequestDto, @MappingTarget UserEntity userEntity);

    void updateUserResponseDtoFromUserEntity(UserEntity userEntity, @MappingTarget UserResponseDto userResponseDto);
}
