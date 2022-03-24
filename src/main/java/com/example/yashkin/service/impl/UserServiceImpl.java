package com.example.yashkin.service.impl;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.UserMapper;
import com.example.yashkin.mongo.UserMongoRepository;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.UserService;
import com.example.yashkin.utils.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "uc")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserMongoRepository userMongoRepository;

    public UserServiceImpl(UserRepository userRepository, @Qualifier("userMapperImpl") UserMapper userMapper, PasswordEncoder passwordEncoder, UserMongoRepository userMongoRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userMongoRepository = userMongoRepository;
    }

    @Cacheable(cacheNames = "users")
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

    @Cacheable(cacheNames = "users", key = "#id")
    @Transactional
    @Override
    public UserResponseDto getById(Long id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(Translator.toLocale("user.exception.not_found_by_id"), id))
        );
        if (!userMongoRepository.existsById(userEntity.getId())) {
            userMongoRepository.insert(userEntity);
        }
        System.out.println("FROM_MONGO: Name = " + userMongoRepository.findById(userEntity.getId()).get().getFirstName());
        System.out.println("FROM_MONGO: ID = " + userMongoRepository.findById(userEntity.getId()).get().getId());

        UserResponseDto responseDto = userMapper.userResponseDtoFromUserEntity(userEntity);
        log.info("user got by id");
        return responseDto;

    }

    @CacheEvict(cacheNames = "users", allEntries = true)
    @Transactional
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        log.info("Вызов метода add User");
        UserEntity entity = userMapper.userEntityFromUserRequestDto(userRequestDto);

        entity.setId(null);
        entity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(entity);
        userMongoRepository.save(entity);

        UserResponseDto responseDto = userMapper.userResponseDtoFromUserEntity(entity);
        log.info("user added");
        return responseDto;

    }

    @CacheEvict(cacheNames = "users", allEntries = true)
    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(Translator.toLocale("user.exception.not_found_by_id"), id))
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

    @Caching(evict = {@CacheEvict(cacheNames = "users", key = "#id"),
            @CacheEvict(cacheNames = "users", allEntries = true)})
    @Transactional
    @Override
    public UserResponseDto deleteUser(Long id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(Translator.toLocale("user.exception.not_found_by_id"), id))
        );
        userRepository.delete(entity);
        UserResponseDto responseDto = userMapper.userResponseDtoFromUserEntity(entity);
        log.info("user deleted");
        return responseDto;
    }
}
