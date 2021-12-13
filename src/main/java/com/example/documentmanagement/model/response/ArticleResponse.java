package com.example.documentmanagement.model.response;

import com.example.documentmanagement.entity.Article;
import com.example.documentmanagement.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String author;
    private String articleContent;
    private LocalDateTime publishDate;
    private int starCount;
    private List<Review> reviews;

    public static ArticleResponse fromModel(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .author(article.getAuthor())
                .articleContent(article.getArticleContent())
                .publishDate(article.getPublishDate())
                .starCount(article.getStarCount())
                .reviews(article.getReviews())
                .build();

    }
}
