package com.campmeme.website.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection = "memes")
public class Meme {

    @Transient
    public static final String SEQUENCE_NAME = "memes_sequence";

    @Id
    private long id;

    @Indexed
    private List<String> tags;

    @Field(name="file_path")
    @Indexed
    private String filePath;

    @Field(name="like_count")
    private int likeCount;

    @Field(name="contributor")
    private String contributorId;

    public Meme(String filePath, List<String> tags, int likeCount, String contributorId) {
        super();
        this.tags = tags;
        this.filePath = filePath;
        this.likeCount = likeCount;
        this.contributorId = contributorId;
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

    public String getContributorId() {
        return contributorId;
    }

    public void setContributorId(String contributorId) {
        this.contributorId = contributorId;
    }
}