package com.satish.postservice.model.request;

import lombok.*;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PostRequest {
    private String userId;
    private String title;
    private String content;
    private String category;
}
