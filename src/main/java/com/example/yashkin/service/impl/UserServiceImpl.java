package com.example.yashkin.service.impl;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.UserMapper;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserResponseDto getById(UUID id) throws NullPointerException {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with ID = ' ' not found")
        );

        UserResponseDto responseDto = UserMapper.INSTANCE.userResponseDtoFromUserEntity(userEntity);

        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        UserEntity entity = new UserEntity("FirstName1", "LastName1", "Executor1");
        userRepository.save(entity);

        UserResponseDto responseDto = UserMapper.INSTANCE.userResponseDtoFromUserEntity(entity);
        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) throws NullPointerException {
        UserEntity entity = userRepository.findByName(userRequestDto.getFirstName(), userRequestDto.getLastName()).orElseThrow(
                () -> new NotFoundException("User with ID = ' ' not found")
        );
        UserEntity entity1 = UserMapper.INSTANCE.userEntityFromUserRequestDto(userRequestDto);
        userRepository.save(entity1);
        UserResponseDto responseDto = UserMapper.INSTANCE.userResponseDtoFromUserEntity(entity1);
        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto deleteUser(UUID id) throws NullPointerException {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with ID = ' ' not found")
        );
        userRepository.delete(entity);
        UserResponseDto responseDto = UserMapper.INSTANCE.userResponseDtoFromUserEntity(entity);
        return responseDto;

    }
}
