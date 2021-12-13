package com.example.documentmanagement.model.response;

import com.example.documentmanagement.entity.Article;
import com.example.documentmanagement.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private String reviewer;
    private String reviewContent;
    private Article article;

    public static ReviewResponse fromModel(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .reviewContent(review.getReviewContent())
                .reviewer(review.getReviewer())
                .article(review.getArticle())
                .build();

    }
}
