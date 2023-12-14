package com.campmeme.website.repository;

import com.campmeme.website.entity.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MemeRepository extends MongoRepository<Meme, Long> {
}