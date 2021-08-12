package com.example.yashkin.service;

import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;

/**
 * Interface with methods for user management
 * @author Oleg Yaskin
 */
public interface UserService {

    /**
     * Method for getting a user from the database by ID
     * @param id Long
     * @return UserResponseDto
     */
    UserResponseDto getById(Long id);

    /**
     * Method for adding a user to the database
     * @param userRequestDto UserRequestDto
     * @return UserResponseDto
     */
    UserResponseDto addUser(UserRequestDto userRequestDto);

    /**
     * Method for updating the user
     * @param id Long
     * @param userRequestDto UserRequestDto
     * @return UserResponseDto
     */
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    /**
     * Method for deleting the user
     * @param id Long
     * @return UserResponseDto
     */
    UserResponseDto deleteUser(Long id);

}
