package com.example.yashkin.entity;

import com.example.yashkin.model.ProjectStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String projectName;

    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "customer")
    private String customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<TaskEntity> tasks;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity userCustomer;

    public ProjectEntity() {
    }

    public ProjectEntity(String projectName) {
        this.projectName = projectName;
    }

    public ProjectEntity(Long id, String projectName, ProjectStatus status, String customer, Set<TaskEntity> tasks, UserEntity userCustomer) {
        this.id = id;
        this.projectName = projectName;
        this.status = status;
        this.customer = customer;
        this.tasks = tasks;
        this.userCustomer = userCustomer;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
