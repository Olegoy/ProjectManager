package com.example.yashkin.entity;

import com.example.yashkin.model.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Document(collection = "Users")
@Entity
@Data
@Builder
@Table(name = "users")
public class UserEntity {

    @Indexed(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(value = "first_name")
    @Column(name = "first_name")
    private String firstName;

    @Field(value = "last_name")
    @Column(name = "last_name")
    private String lastName;

    @Field(value = "login")
    @Column(nullable = false, unique = true)
    private String login;

    @Field(value = "password")
    @Column(nullable = true)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", schema = "public", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<TaskEntity> tasksAsAuthor;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },  mappedBy = "executor")
    private Set<TaskEntity> tasksAsExecutor;

    public UserEntity() {
    }

    public UserEntity(Long id, String firstName, String lastName, String login, String password, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity(Long id, String firstName, String lastName, String login, String password, Set<Role> roles, Set<TaskEntity> tasksAsAuthor, Set<TaskEntity> tasksAsExecutor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.tasksAsAuthor = tasksAsAuthor;
        this.tasksAsExecutor = tasksAsExecutor;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<TaskEntity> getTasksAsAuthor() {
        return tasksAsAuthor;
    }

    public void setTasksAsAuthor(Set<TaskEntity> tasksAsAuthor) {
        this.tasksAsAuthor = tasksAsAuthor;
    }

    public Set<TaskEntity> getTasksAsExecutor() {
        return tasksAsExecutor;
    }

    public void setTasksAsExecutor(Set<TaskEntity> tasksAsExecutor) {
        this.tasksAsExecutor = tasksAsExecutor;
    }
}
