package com.example.yashkin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paying")
public class PayingEntity {

    @Id
    @Column(name = "project_name")
    private String projectName;

    public PayingEntity() {
    }

    public PayingEntity(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
