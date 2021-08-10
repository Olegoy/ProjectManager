package com.example.yashkin.service.impl;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.UserMapper;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.getUserByLogin(login).orElseThrow(() -> {
            UsernameNotFoundException exception = new UsernameNotFoundException(
                    String.format("Пользователь с логином #%s не существует", login)
            );
            log.error(exception.getMessage(), exception);
            throw exception;
        });
    }

    public UserServiceImpl(UserRepository userRepository, @Qualifier("userMapperImpl") UserMapper INSTANCE) {
        this.userRepository = userRepository;
        this.userMapper = INSTANCE;
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

        UserEntity entity = userMapper.userEntityFromUserRequestDto(userRequestDto);
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
