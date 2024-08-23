package com.example.postserver.posts;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String imageId;
    private int uploaderId;
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime uploadDatetime;
    private String contents;
    private String status;

    @PrePersist
    protected void onCreate() {
        uploadDatetime = ZonedDateTime.now();
    }

    public PostEntity() {
    }

    public PostEntity(int postId, String imageId, int uploaderId, ZonedDateTime uploadDatetime, String contents, String status) {
        this.postId = postId;
        this.imageId = imageId;
        this.uploaderId = uploaderId;
        this.uploadDatetime = uploadDatetime;
        this.contents = contents;
        this.status = status;
    }

    public PostEntity(String imageId, int uploaderId, String contents) {
        this.imageId = imageId;
        this.uploaderId = uploaderId;
        this.contents = contents;
    }

    public PostEntity(PostRequest request) {
        this(request.getImageId(), request.getUploaderId(), request.getContents());
    }

    public int getPostId() {
        return postId;
    }

    public String getImageId() {
        return imageId;
    }

    public int getUploaderId() {
        return uploaderId;
    }

    public ZonedDateTime getUploadDatetime() {
        return uploadDatetime;
    }

    public String getContents() {
        return contents;
    }

    public String getStatus() {
        return status;
    }
}
