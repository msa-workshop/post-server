package com.example.postserver.posts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<SocialPost> getAllPosts() {
        List<SocialPost> allPosts = postService.getAllPosts();
        return allPosts;
    }

    @GetMapping("/user/{userId}")
    public List<SocialPost> getAllPostsByUser(@PathVariable("userId") int userId) {
        logger.info("User API : {}", userId);
        return postService.getAllPostsByUploaderId(userId);
    }

    @GetMapping("/v2/user/{userId}")
    public List<SocialPostV2> getAllPostsByUserV2(@PathVariable("userId") int userId) {
        return postService.getAllPostsByUploaderId(userId).stream().map(SocialPostV2::fromSocialPostV1).toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<SocialPost> getPostById(@PathVariable int id) {
        SocialPost post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public SocialPost createPost(@RequestBody PostRequest post) {
        return postService.createPost(post);
    }

    @DeleteMapping("/deactivate/{userId}")
    public ResponseEntity<?> deactivateUser(@PathVariable int userId) {
        boolean result = postService.deactivatePost(userId);
        return result ? ResponseEntity.ok(true) : ResponseEntity.internalServerError().build();
    }

    @PutMapping("/activate/{userId}")
    public ResponseEntity<?> activateUser(@PathVariable int userId) {
        boolean result = postService.activatePost(userId);
        return result ? ResponseEntity.ok(true) : ResponseEntity.internalServerError().build();
    }
}
