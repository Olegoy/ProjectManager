package com.example.yashkin.repository;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, UUID> {

    Optional<ReleaseEntity> findByName(Integer version);

}
