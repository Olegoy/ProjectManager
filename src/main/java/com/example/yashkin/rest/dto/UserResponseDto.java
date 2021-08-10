package com.example.yashkin.rest.dto;

import com.example.yashkin.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Пользователь")
public class UserResponseDto {

    @Schema(description = "ID пользователя")
    private Long id;

    @Schema(description = "Имя пользователя")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    private String lastName;

    @Schema(description = "Login пользователя")
    private String login;

    @Schema(description = "Роли пользователя")
    private Set<Role> roles;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id) {
        this.id = id;
    }

    public UserResponseDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserResponseDto(String firstName, String lastName, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public UserResponseDto(Long id, String firstName, String lastName, String login, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
