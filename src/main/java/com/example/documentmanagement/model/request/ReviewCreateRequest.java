package com.example.documentmanagement.model.request;

import com.example.documentmanagement.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateRequest {
    private String reviewer;
    private String reviewContent;
    private Long articleId;
    private Article article;
}
