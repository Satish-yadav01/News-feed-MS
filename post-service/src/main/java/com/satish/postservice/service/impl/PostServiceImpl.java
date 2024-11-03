package com.satish.postservice.service.impl;

import com.satish.postservice.model.entity.Post;
import com.satish.postservice.model.request.PostRequest;
import com.satish.postservice.model.response.PostResponse;
import com.satish.postservice.repo.PostRepository;
import com.satish.postservice.service.PostService;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final RedisTemplate<String, HashMap<String, List<String>>> redisTemplate;

    private static String keyName = "posts";

    public PostServiceImpl(PostRepository postRepository, RedisTemplate<String, HashMap<String, List<String>>> redisTemplate) {
        this.postRepository = postRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
//    @CachePut(/*cacheNames = "posts", */key = "#result.postId", value = "postId" ,unless = "#result == null")
    public PostResponse publishPost(PostRequest postRequest) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .author(postRequest.getUserId())
                .category(postRequest.getCategory())
                .build();

        Post savedPost = postRepository.save(post);

        Object postIdObject = redisTemplate.opsForHash().get(keyName, savedPost.getAuthor());
        System.out.println("postIdObject : "+postIdObject);

        List<String> postIds = new ArrayList<>();
        if (postIdObject != null && postIdObject instanceof List) {
            // Safely cast to List<String>
            postIds = (List<String>) postIdObject;
            System.out.println("Post IDs: " + postIds);
        } else {
            System.out.println("No post IDs found or incompatible type.");
        }

        HashMap<String, List<String>> hashMap = new HashMap<>();
        postIds.add(savedPost.getId());
        hashMap.put(savedPost.getAuthor(), postIds);
        redisTemplate.opsForHash().putAll(keyName, hashMap);

        if(savedPost == null) {
            return null;
        }
        PostResponse postResponse = PostResponse.builder()
                .postId(savedPost.getId())
                .message("Post created successfully")
                .userId(savedPost.getAuthor())
                .build();


        return postResponse;
    }
}
