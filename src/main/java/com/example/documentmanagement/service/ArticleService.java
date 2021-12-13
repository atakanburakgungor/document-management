package com.example.documentmanagement.service;

import com.example.documentmanagement.components.JsonMergePatcher;
import com.example.documentmanagement.entity.Article;
import com.example.documentmanagement.exception.NoDataFoundException;
import com.example.documentmanagement.model.request.ArticleCreateRequest;
import com.example.documentmanagement.model.request.ArticleUpdateRequest;
import com.example.documentmanagement.model.response.ArticleResponse;
import com.example.documentmanagement.repository.ArticleRepository;
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
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final JsonMergePatcher patcher;

    public ArticleService(ArticleRepository articleRepository, JsonMergePatcher patcher) {
        this.articleRepository = articleRepository;
        this.patcher = patcher;
    }

    public ArticleResponse createArticle(ArticleCreateRequest createRequest) {
        log.info("ArticleCreateService -> createArticle started! {}", createRequest.getClass().getName());
        Article article = copyProperties(createRequest);
        Article savedEntity = articleRepository.save(article);
        return ArticleResponse.fromModel(savedEntity);
    }

    public Long deleteArticle(Long id) {
        Article existArticle = findArticleById(id);
        articleRepository.delete(existArticle);
        return id;
    }

    private Article copyProperties(ArticleCreateRequest createRequest) {
        Article article = new Article();
        CommonUtils.copyFromTo(createRequest, article);
        article.assignParentToChilds();
        return article;
    }

    public Article findArticleById(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoDataFoundException("document.service.noDataFound"));
        return article;
    }

    public List<ArticleResponse> listArticles(Predicate predicate, Pageable pageable) {
        log.info("ArticleService -> listArticles started! {} {} {} {}", pageable.getOffset(), pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        List<Article> articles = articleRepository.findAll(predicate, pageable).getContent();
        return articles.stream().map(ArticleResponse::fromModel).collect(Collectors.toList());
    }

    public ArticleResponse patchArticle(Long id, ArticleUpdateRequest articleUpdateRequest) {
        Article existingArticle = findArticleById(id);
        String articleStr = CommonUtils.serializeToJson(articleUpdateRequest);
        Article patched = patcher.mergePatch(articleStr, existingArticle);
        existingArticle.assignParentToChilds();
        articleRepository.save(patched);
        return ArticleResponse.fromModel(patched);
    }

    public ArticleResponse retrieveArticle(Long id) {
        Article existingArticle = findArticleById(id);
        return ArticleResponse.fromModel(existingArticle);
    }
}
