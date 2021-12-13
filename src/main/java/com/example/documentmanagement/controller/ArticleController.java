package com.example.documentmanagement.controller;

import com.example.documentmanagement.controller.api.ArticleApi;
import com.example.documentmanagement.model.DataResponse;
import com.example.documentmanagement.model.Response;
import com.example.documentmanagement.model.request.ArticleCreateRequest;
import com.example.documentmanagement.model.request.ArticleUpdateRequest;
import com.example.documentmanagement.model.response.ArticleResponse;
import com.example.documentmanagement.service.ArticleService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ArticleController extends BaseController implements ArticleApi {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<Response<ArticleResponse>> createArticle(ArticleCreateRequest article) {
        return new ResponseEntity<>(respond(articleService.createArticle(article)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response<Long>> deleteArticle(Long id) {
        return new ResponseEntity<>(respond(articleService.deleteArticle(id)), HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Response<ArticleResponse>> patchArticle(Long id, ArticleUpdateRequest articleUpdateRequest) {
        return new ResponseEntity<>(respond(articleService.patchArticle(id, articleUpdateRequest)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<ArticleResponse>> retrieveArticle(Long id) {
        return new ResponseEntity<>(respond(articleService.retrieveArticle(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<DataResponse<ArticleResponse>>> listArticle(Predicate predicate, Pageable pageable) {
        return new ResponseEntity<>(respond(articleService.listArticles(predicate, pageable)), HttpStatus.OK);
    }
}
