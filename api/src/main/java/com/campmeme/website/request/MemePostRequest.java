package com.campmeme.website.request;

import org.bson.types.ObjectId;

import java.util.List;

public record MemePostRequest(String file_path, String contributor, List<String> tags) {

}
