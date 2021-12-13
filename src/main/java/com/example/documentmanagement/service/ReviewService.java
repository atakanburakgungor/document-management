package com.example.documentmanagement.service;

import com.example.documentmanagement.components.JsonMergePatcher;
import com.example.documentmanagement.entity.Article;
import com.example.documentmanagement.entity.Review;
import com.example.documentmanagement.exception.NoDataFoundException;
import com.example.documentmanagement.model.request.ReviewCreateRequest;
import com.example.documentmanagement.model.request.ReviewUpdateRequest;
import com.example.documentmanagement.model.response.ReviewResponse;
import com.example.documentmanagement.repository.ReviewRepository;
import com.example.documentmanagement.util.CommonUtils;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ArticleService articleService;
    private final JsonMergePatcher patcher;

    public ReviewService(ReviewRepository reviewRepository, ArticleService articleService, JsonMergePatcher patcher) {
        this.reviewRepository = reviewRepository;
        this.articleService = articleService;
        this.patcher = patcher;
    }

    public ReviewResponse createReview(ReviewCreateRequest createRequest) {
        log.info("ReviewCreateService -> createReview started! {}", createRequest.getClass().getName());
        Article article = retrieveArticle(createRequest.getArticleId());
        Review review = copyProperties(createRequest, article);
        Review savedEntity = reviewRepository.save(review);
        return ReviewResponse.fromModel(savedEntity);
    }

    public Long deleteReview(Long id) {
        Review existReview = findReviewById(id);
        reviewRepository.delete(existReview);
        return id;
    }

    private Review copyProperties(ReviewCreateRequest createRequest, Article article) {
        Review review = new Review();
        CommonUtils.copyFromTo(createRequest, review);
        review.setArticle(article);
        return review;
    }

    public Review findReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoDataFoundException("document.service.noDataFound"));
        return review;
    }

    public List<ReviewResponse> listReviews(Predicate predicate, Pageable pageable) {
        log.info("ReviewService -> listReviews started! {} {} {} {}", pageable.getOffset(), pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        List<Review> Reviews = reviewRepository.findAll(predicate, pageable).getContent();
        return Reviews.stream().map(ReviewResponse::fromModel).collect(Collectors.toList());
    }

    public ReviewResponse updateReview(Long id, ReviewUpdateRequest updateRequest) {
        Review existingReview = findReviewById(id);
        existingReview.setReviewContent(updateRequest.getReviewContent());
        existingReview.setReviewer(updateRequest.getReviewer());
        Review savedEntity = reviewRepository.save(existingReview);
        return ReviewResponse.fromModel(savedEntity);
    }

    public ReviewResponse retrieveReview(Long id) {
        Review existingReview = findReviewById(id);
        return ReviewResponse.fromModel(existingReview);
    }

    private Article retrieveArticle(Long articleId) {
        return articleService.findArticleById(articleId);
    }
}
