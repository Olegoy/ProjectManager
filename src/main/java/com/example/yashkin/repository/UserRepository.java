package com.example.yashkin.repository;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByName(String firstName, String lastName);

}
