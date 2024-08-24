package com.example.postserver.posts;

import java.time.ZonedDateTime;

public class PostActivity {
    private int userId;
    private ZonedDateTime lastUpdatedDatetime;
    private String lastUpdatedId;

    public PostActivity(int userId, ZonedDateTime lastUpdatedDatetime, String lastUpdatedId) {
        this.userId = userId;
        this.lastUpdatedDatetime = lastUpdatedDatetime;
        this.lastUpdatedId = lastUpdatedId;
    }

    public int getUserId() {
        return userId;
    }

    public ZonedDateTime getLastUpdatedDatetime() {
        return lastUpdatedDatetime;
    }

    public String getLastUpdatedId() {
        return lastUpdatedId;
    }
}
