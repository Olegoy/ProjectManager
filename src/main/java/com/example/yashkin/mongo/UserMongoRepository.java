package com.example.yashkin.mongo;

import com.example.yashkin.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface UserMongoRepository extends MongoRepository<UserEntity, Long> {
}
