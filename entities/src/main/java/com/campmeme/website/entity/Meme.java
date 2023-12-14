package com.campmeme.website.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection = "memes")
public class Meme {

    @Id
    private long id;

    @Indexed
    private List<String> tags;

    @Field(name="file_path")
    private String filePath;

    @Field(name="like_count")
    private int likeCount;

    public Meme(long id, List<String> tags, String filePath, int likeCount) {
        super();
        this.id = id;
        this.tags = tags;
        this.filePath = filePath;
        this.likeCount = likeCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}