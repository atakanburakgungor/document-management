package com.example.documentmanagement.repository;

import com.example.documentmanagement.entity.Article;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>, QuerydslPredicateExecutor<Article> {
}
