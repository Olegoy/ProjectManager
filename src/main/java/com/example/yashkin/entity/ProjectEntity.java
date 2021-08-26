package com.example.yashkin.entity;

import com.example.yashkin.model.ProjectStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String projectName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private Set<TaskEntity> tasks;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    public ProjectEntity() {
    }

    public ProjectEntity(String projectName) {
        this.projectName = projectName;
    }

    public ProjectEntity(Long id, String projectName, ProjectStatus status, Set<TaskEntity> tasks, UserEntity customer) {
        this.id = id;
        this.projectName = projectName;
        this.status = status;
        this.tasks = tasks;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }
}
