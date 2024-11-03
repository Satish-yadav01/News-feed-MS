package com.satish.postservice.controller;

import com.satish.postservice.model.request.PostRequest;
import com.satish.postservice.model.response.PostResponse;
import com.satish.postservice.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/publish")
    public ResponseEntity<PostResponse> publishPost(@RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.publishPost(postRequest);
        return ResponseEntity.ok(postResponse);
    }
}
