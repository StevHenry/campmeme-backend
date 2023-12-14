package com.campmeme.website.request;

public class UserReducedDataResponse extends OperationSuccess {

    private String username;
    private String id;

    public UserReducedDataResponse(String username, String id) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
