package com.example.yashkin.repository;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    Optional<TaskEntity> findByName(String name);

}
