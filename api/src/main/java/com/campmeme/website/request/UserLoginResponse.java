package com.campmeme.website.request;

public class UserLoginResponse extends OperationSuccess {

    private String username;
    private int group;

    private String id;

    public UserLoginResponse(String username, int group, String id) {
        this.username = username;
        this.group = group;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
