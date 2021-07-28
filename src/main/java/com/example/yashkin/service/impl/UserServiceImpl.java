package com.example.yashkin.service.impl;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.UserMapper;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper INSTANCE;

    public UserServiceImpl(UserRepository userRepository, UserMapper INSTANCE) {
        this.userRepository = userRepository;
        this.INSTANCE = INSTANCE;
    }

    @Transactional
    @Override
    public UserResponseDto getById(Long id) throws NullPointerException {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with ID = %d not found", id))
        );

        UserResponseDto responseDto = INSTANCE.userResponseDtoFromUserEntity(userEntity);

        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        UserEntity entity = INSTANCE.userEntityFromUserRequestDto(userRequestDto);
        userRepository.save(entity);

        UserResponseDto responseDto = INSTANCE.userResponseDtoFromUserEntity(entity);
        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) throws NullPointerException {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with ID = %d not found", id))
        );
        entity.setFirstName(userRequestDto.getFirstName());
        entity.setLastName(userRequestDto.getLastName());
        entity.setRole(userRequestDto.getRole());
        UserResponseDto responseDto = INSTANCE.userResponseDtoFromUserEntity(entity);
        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto deleteUser(Long id) throws NullPointerException {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with ID = %d not found", id))
        );
        userRepository.delete(entity);
        UserResponseDto responseDto = INSTANCE.userResponseDtoFromUserEntity(entity);
        return responseDto;

    }
}
