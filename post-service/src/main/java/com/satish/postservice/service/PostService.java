package com.satish.postservice.service;

import com.satish.postservice.model.request.PostRequest;
import com.satish.postservice.model.response.PostResponse;

/**
 * @author : Satish Yadav
 * @purpose :
 */
public interface PostService {
    PostResponse publishPost(PostRequest postRequest);
}
