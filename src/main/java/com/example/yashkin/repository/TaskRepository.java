package com.example.yashkin.repository;

import com.example.yashkin.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> getTaskEntitiesByStatus_DoneIsNot();

    List<TaskEntity> findAllByRelease(Long releaseId);

    List<TaskEntity> findAllByProjectId(Long projectId);
}
