package com.example.postserver.posts.uploader;

public class UploaderInfo {
    private int userId;
    private String username;
    private String email;

    public UploaderInfo() {
    }

    public UploaderInfo(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
