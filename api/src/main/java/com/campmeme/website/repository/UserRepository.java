package com.campmeme.website.repository;

import com.campmeme.website.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(ObjectId id);
}
