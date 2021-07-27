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
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String projectName;

    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "customer")
    private String customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<TaskEntity> tasks;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity user_customer;

    public ProjectEntity() {
    }

    public ProjectEntity(String projectName) {
        this.projectName = projectName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
