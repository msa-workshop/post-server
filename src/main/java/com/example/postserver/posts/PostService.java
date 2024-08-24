package com.example.postserver.posts;

import com.example.postserver.posts.uploader.UploaderInfo;
import com.example.postserver.posts.uploader.UploaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UploaderService uploaderService;

    public PostService(PostRepository postRepository, UploaderService uploaderService) {
        this.postRepository = postRepository;
        this.uploaderService = uploaderService;
    }

    private SocialPost updateUploaderName(SocialPost post) {
        UploaderInfo uploader = uploaderService.getUserInfo(post.getUploaderId());
        return new SocialPost(post, uploader.getUsername());
    }

    public List<SocialPost> getAllPosts() {
        return postRepository.findAll().stream().map(SocialPost::fromPostEntity).map(this::updateUploaderName).toList();
    }

    public List<SocialPost> getAllPostsByUploaderId(int uploaderId) {
        return postRepository.findByUploaderId(uploaderId).stream().map(SocialPost::fromPostEntity).map(this::updateUploaderName).toList();
    }

    public SocialPost getPostById(int postId) {
        return SocialPost.fromPostEntity(postRepository.findById(postId).orElse(null));
    }

    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public SocialPost createPost(PostRequest post) {
        PostEntity savedPost = postRepository.save(new PostEntity(post));

        return SocialPost.fromPostEntity(savedPost);
    }


}
