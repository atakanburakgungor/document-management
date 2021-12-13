package com.example.documentmanagement.model.request;

import com.example.documentmanagement.entity.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleUpdateRequest {
    private String title;
    private String author;
    private String articleContent;
    private Integer starCount;
    private List<Review> reviews;
}
