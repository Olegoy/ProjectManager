package com.example.yashkin.repository;

import com.example.yashkin.entity.PayingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayingRepository extends JpaRepository<PayingEntity, String> {
}
