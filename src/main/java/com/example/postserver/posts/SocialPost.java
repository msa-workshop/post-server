package com.example.postserver.posts;

import java.time.ZonedDateTime;

public class SocialPost {

    private final int postId;
    private final String imageId;
    private final int uploaderId;
    private final ZonedDateTime uploadDatetime;
    private final String contents;
    private final String uploaderName;
    private final String status;

    public SocialPost(int postId, String imageId, int uploaderId, ZonedDateTime uploadDatetime, String contents, String uploaderName, String status) {
        this.postId = postId;
        this.imageId = imageId;
        this.uploaderId = uploaderId;
        this.uploadDatetime = uploadDatetime;
        this.contents = contents;
        this.uploaderName = uploaderName;
        this.status = status;
    }

    private SocialPost(PostEntity post) {
        this(post.getPostId(), post.getImageId(), post.getUploaderId(), post.getUploadDatetime(), post.getContents(), null, post.getStatus());
    }

    public SocialPost(SocialPost post, String uploaderName) {
        this(post.getPostId(), post.getImageId(), post.getUploaderId(), post.getUploadDatetime(), post.getContents(), uploaderName, post.getStatus());
    }

    public static SocialPost fromPostEntity(PostEntity post) {
        return new SocialPost(post);
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

    public String getUploaderName() {
        return uploaderName;
    }

    public String getStatus() {
        return status;
    }
}
