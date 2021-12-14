package com.example.documentmanagement;

import com.example.documentmanagement.entity.Article;
import com.example.documentmanagement.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Testcontainers
public class DatabaseIntegrationTests extends AbstractBase {
    @Autowired
    private ArticleRepository articleRepository;

    @Container
    public static JdbcDatabaseContainer postgres = new PostgreSQLContainer("postgres")
            .withDatabaseName("integration_testing")
            .withUsername("integrationTestUser")
            .withPassword("integrationTestPass").withInitScript("db/document-schema.sql");


    @Test
    void testPostgresqlInstanceIsRunning() {
        Assert.assertTrue(postgres.isRunning());
        Assert.assertTrue(postgres.isCreated());
    }

    @Test
    public void testWriteToDb_afterBoot_shouldHaveEntries() {
        writeInsignificantEntriesToDb();
        List<Article> createdArticles = new ArrayList<>();
        articleRepository.findAll().forEach(createdArticles::add);
        Assert.assertNotNull(createdArticles);
    }

    private void writeInsignificantEntriesToDb() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setTitle("first article");
        article.setReviews(Collections.EMPTY_LIST);
        article.setArticleContent("article content");
        article.setAuthor("burak gungor");
        article.setStarCount(0);

        Article article2 = new Article();
        article.setTitle("article title");
        article.setReviews(Collections.EMPTY_LIST);
        article.setArticleContent("article content");
        article.setAuthor("burak gungor");
        article.setStarCount(0);

        articles.add(article);
        articles.add(article2);
        articleRepository.saveAll(articles);
    }
}
