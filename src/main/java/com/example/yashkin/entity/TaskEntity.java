package com.example.yashkin.entity;

import com.example.yashkin.model.TaskStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
    @ManyToOne
    @JoinColumn(name = "executor_id")
    private UserEntity executor;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "priority")
    private Integer priority;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity projectId;

    @ManyToOne
    @JoinColumn(name = "release_id")
    private ReleaseEntity release;

    public TaskEntity() {
    }

    public TaskEntity(Long id, String name, UserEntity author, String type, TaskStatus status, Integer priority, ProjectEntity projectId, ReleaseEntity release) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.release = release;
    }

    public TaskEntity(Long id, String name, UserEntity author, UserEntity executor, String type, TaskStatus status, Integer priority, ProjectEntity projectId, ReleaseEntity release) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public UserEntity getExecutor() {
        return executor;
    }

    public void setExecutor(UserEntity executor) {
        this.executor = executor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public ProjectEntity getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectEntity projectId) {
        this.projectId = projectId;
    }

    public ReleaseEntity getRelease() {
        return release;
    }

    public void setRelease(ReleaseEntity release) {
        this.release = release;
    }
}
