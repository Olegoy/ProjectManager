package com.example.yashkin.service.impl;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.mappers.UserMapper;
import com.example.yashkin.model.Role;
import com.example.yashkin.mongo.UserMongoRepository;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final Long ID = 1L;
    private static final String USER_FIRST_NAME = "user";
    private static final String USER_LAST_NAME = "userov";
    private static final String USER_LOGIN = "user";
    private static final String USER_PASSWORD = "user";

    PasswordEncoder passwordEncoder;

    UserMapper userMapper;

    @Mock
    private UserRepository userRepository;
    private UserMongoRepository userMongoRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        // init mocks
        passwordEncoder = new BCryptPasswordEncoder(12);
        userMapper = new UserMapper() {
            @Override
            public UserEntity userEntityFromUserRequestDto(UserRequestDto userRequestDto) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(userRequestDto.getId());
                userEntity.setFirstName(userRequestDto.getFirstName());
                userEntity.setLastName(userRequestDto.getLastName());
                userEntity.setLogin(userRequestDto.getLogin());
                userEntity.setPassword(userRequestDto.getPassword());
                userEntity.setRoles(userRequestDto.getRoles());
                return userEntity;
            }

            @Override
            public UserResponseDto userResponseDtoFromUserEntity(UserEntity userEntity) {
                UserResponseDto responseDto = new UserResponseDto();
                responseDto.setId(userEntity.getId());
                responseDto.setFirstName(userEntity.getFirstName());
                responseDto.setLastName(userEntity.getLastName());
                responseDto.setLogin(userEntity.getLogin());
                responseDto.setPassword(userEntity.getPassword());
                responseDto.setRoles(userEntity.getRoles());
                return responseDto;
            }

            @Override
            public void updateUserEntityFromUserRequestDto(UserRequestDto userRequestDto, UserEntity userEntity) {

            }

            @Override
            public void updateUserResponseDtoFromUserEntity(UserEntity userEntity, UserResponseDto userResponseDto) {

            }
        };
        userService = new UserServiceImpl(userRepository, userMapper, passwordEncoder, userMongoRepository);
    }

    @Test
    public void getUsers() {
        UserEntity testUser1 = new UserEntity(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        UserEntity testUser2 = new UserEntity(ID +1, USER_FIRST_NAME + 1, USER_LAST_NAME, USER_LOGIN +1, USER_PASSWORD, Set.of(Role.USER));
        UserRequestDto requestTestUser1 = new UserRequestDto(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        UserRequestDto requestTestUser2 = new UserRequestDto(ID +1, USER_FIRST_NAME + 1, USER_LAST_NAME, USER_LOGIN +1, USER_PASSWORD, Set.of(Role.USER));
        UserResponseDto responseTestUser1 = new UserResponseDto(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        UserResponseDto responseTestUser2 = new UserResponseDto(ID +1, USER_FIRST_NAME + 1, USER_LAST_NAME, USER_LOGIN +1, USER_PASSWORD, Set.of(Role.USER));
        userService.addUser(requestTestUser1);
        userService.addUser(requestTestUser2);
        List<UserEntity> entities = List.of(testUser1, testUser2);
        Mockito.when(userRepository.findAll()).thenReturn(entities);

        List<UserResponseDto> responseDtoListExpected = List.of(responseTestUser1, responseTestUser2);
        List<UserResponseDto> responseDtoListActual = userService.getUsers();
        Assert.assertEquals(responseDtoListExpected.get(1).getFirstName(), responseDtoListActual.get(1).getFirstName());
    }

    @Test
    public void getById() {
        UserEntity expected = new UserEntity(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        UserRequestDto requestDto = new UserRequestDto(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(expected));

        userService.addUser(requestDto);

        UserResponseDto actual = userService.getById(ID);

        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void addUser() {
        UserRequestDto expected = new UserRequestDto(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));

        UserResponseDto actual = userService.addUser(expected);

        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void updateUser() {
        UserEntity testUser = new UserEntity(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        UserRequestDto requestTestUser = new UserRequestDto(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        UserResponseDto responseDtoTestUser = userService.addUser(requestTestUser);

        Long testID = responseDtoTestUser.getId();
        String newFirstName = "Ivan1";
        UserRequestDto expected = new UserRequestDto(ID, newFirstName, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(testUser));
        UserResponseDto actual = userService.updateUser(testID, expected);

        Assert.assertEquals(newFirstName, actual.getFirstName());
    }

    @Test
    public void deleteUser() {
        UserEntity testUser = new UserEntity(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        UserRequestDto expected = new UserRequestDto(ID, USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PASSWORD, Set.of(Role.USER));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(testUser));
        UserResponseDto actual = userService.addUser(expected);

        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Long testId = actual.getId();
        Assert.assertEquals(actual.getFirstName(), userService.deleteUser(testId).getFirstName());

    }
}