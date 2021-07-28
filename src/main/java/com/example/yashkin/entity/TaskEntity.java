package com.example.yashkin.entity;

import com.example.yashkin.model.TaskStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "executor")
    private String executor;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "version")
    private Integer version;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private Set<ReleaseEntity> releases;

    @ManyToMany(mappedBy = "tasks")
    private Set<UserEntity> users;

    public TaskEntity() {
    }

    public TaskEntity(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public TaskEntity(Long id, String name, String author, String executor, String type, TaskStatus status, Integer priority, Integer version, LocalDateTime dateStart, LocalDateTime dateEnd, ProjectEntity project, Set<ReleaseEntity> releases, Set<UserEntity> users) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.version = version;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.project = project;
        this.releases = releases;
        this.users = users;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
}
