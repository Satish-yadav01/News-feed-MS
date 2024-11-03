package com.satish.postservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String postId;
    private String userId;
    private String message;
}
