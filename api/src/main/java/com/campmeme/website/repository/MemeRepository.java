package com.campmeme.website.repository;

import com.campmeme.website.entity.Meme;
import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MemeRepository extends MongoRepository<Meme, Long> {
    Optional<Meme> findByFilePath(String filePath);

    List<Meme> findByOrderByLikeCountDesc(Limit limit);

    List<Meme> findByTagsContainingIgnoreCase(String tag);
}