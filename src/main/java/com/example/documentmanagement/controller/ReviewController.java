package com.example.documentmanagement.controller;

import com.example.documentmanagement.controller.api.ReviewApi;
import com.example.documentmanagement.model.DataResponse;
import com.example.documentmanagement.model.Response;
import com.example.documentmanagement.model.request.ReviewCreateRequest;
import com.example.documentmanagement.model.request.ReviewUpdateRequest;
import com.example.documentmanagement.model.response.ReviewResponse;
import com.example.documentmanagement.service.ReviewService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class ReviewController extends BaseController implements ReviewApi {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public ResponseEntity<Response<ReviewResponse>> createReview(ReviewCreateRequest request) {
        return new ResponseEntity<>(respond(reviewService.createReview(request)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response<Long>> deleteReview(Long id) {
        return new ResponseEntity<>(respond(reviewService.deleteReview(id)), HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Response<ReviewResponse>> updateReview(Long id, ReviewUpdateRequest request) {
        return new ResponseEntity<>(respond(reviewService.updateReview(id, request)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<ReviewResponse>> retrieveReview(Long id) {
        return new ResponseEntity<>(respond(reviewService.retrieveReview(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<DataResponse<ReviewResponse>>> listReview(Predicate predicate, Pageable pageable, HttpServletResponse response, HttpServletRequest request) {
        return new ResponseEntity<>(respond(reviewService.listReviews(predicate, pageable)), HttpStatus.OK);
    }
}
