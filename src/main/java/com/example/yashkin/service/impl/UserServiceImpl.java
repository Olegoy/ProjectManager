package com.example.yashkin.service.impl;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.UserMapper;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Qualifier("userMapperImpl") UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public List<UserResponseDto> getUsers() {
        List<UserEntity> allUsersEntity = userRepository.findAll();
        List<UserResponseDto> allUsers = allUsersEntity.stream()
                .map(userMapper::userResponseDtoFromUserEntity)
                .collect(Collectors.toList());

        log.info("got all users");
        return allUsers;
    }

    @Transactional
    @Override
    public UserResponseDto getById(Long id) throws NullPointerException {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with ID = %d not found", id))
        );

        UserResponseDto responseDto = userMapper.userResponseDtoFromUserEntity(userEntity);
        log.info("user got by id");
        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        System.out.println("Вызов метода add User");
        UserEntity entity = userMapper.userEntityFromUserRequestDto(userRequestDto);

        entity.setId(null);
        entity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(entity);

        UserResponseDto responseDto = userMapper.userResponseDtoFromUserEntity(entity);
        log.info("user added");
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
        entity.setLogin(userRequestDto.getLogin());
        entity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        entity.setRoles(userRequestDto.getRoles());
        UserResponseDto responseDto = userMapper.userResponseDtoFromUserEntity(entity);
        log.info("user updated");
        return responseDto;

    }

    @Transactional
    @Override
    public UserResponseDto deleteUser(Long id) throws NullPointerException {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with ID = %d not found", id))
        );
        userRepository.delete(entity);
        UserResponseDto responseDto = userMapper.userResponseDtoFromUserEntity(entity);
        log.info("user deleted");
        return responseDto;

    }
}
