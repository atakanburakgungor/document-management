package com.example.documentmanagement;

import com.example.documentmanagement.repository.ArticleRepository;
import com.example.documentmanagement.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(classes = {DocumentManagementApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractBase {

    protected final String ARTICLE_URL = "/article";
    protected final String REVIEW_URL = "/review";

    protected final String APPLICATION_JSON = "application/json;charset=utf-8";
    protected final String CONTENT_TYPE = "Content-Type";
    protected final String ACCEPT = "Accept";
    protected final String SLASH = "/";

    protected final String JSON_PATH = "$";
    protected final String JSON_PATH_ID = "$.id";
    protected final String JSON_PATH_VERSION = "$.id";
    protected final String JSON_PATH_ARTICLE_ID = "$.articleId";
    protected final String JSON_PATH_DATA_ID = "$.data.id";
    protected final String JSON_PATH_TITLE = "$.data.title";
    protected final String JSON_PATH_REVIEW_CONTENT = "$.data.reviewContent";
    protected final String JSON_PATH_REVIEWER = "$.data.reviewer";
    protected final String JSON_PATH_AUTHOR = "$.data.author";
    protected final String JSON_PATH_PUBLISH_DATE = "$.data.publishDate";
    protected final String JSON_PATH_STAR_COUNT = "$.data.starCount";
    protected final String JSON_PATH_ARTICLE_CONTENT = "$.data.articleContent";

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected TestRestTemplate template;
    @Autowired
    public ArticleRepository articleRepository;
    @Autowired
    public ReviewRepository reviewRepository;

    @Autowired
    public ObjectMapper objectMapper;


    @AfterAll
    public void flush() {
        articleRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    public String loadJsonData(String jsonFile) throws IOException {
        ClassLoader classLoader = AbstractBase.class.getClassLoader();
        File file = FileUtils.toFile(classLoader.getResource(jsonFile));
        assert file != null;
        return FileUtils.readFileToString(file, "UTF-8");
    }

    public ResultActions createPOSTRequest(String url, String data) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(url)
                        .header(ACCEPT, APPLICATION_JSON)
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .content(data))
                .andExpect(ResultMatcher.matchAll(
                        result -> System.out.println(result.getResponse().getContentAsString()),
                        status().is(HttpStatus.CREATED.value()),
                        jsonPath(JSON_PATH, notNullValue()),
                        status().is2xxSuccessful()
                ));
    }

    public ResultActions createDELETERequest(String url) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.delete(url)
                        .header(ACCEPT, APPLICATION_JSON)
                        .header(CONTENT_TYPE, APPLICATION_JSON))
                .andExpect(ResultMatcher.matchAll(
                        status().is(HttpStatus.NO_CONTENT.value()),
                        status().is2xxSuccessful()
                ));
    }

    public ResultActions createGETRequest(String url) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(url)
                        .header(ACCEPT, APPLICATION_JSON)
                        .header(CONTENT_TYPE, APPLICATION_JSON))
                .andExpect(ResultMatcher.matchAll(
                        status().is(HttpStatus.OK.value()),
                        status().is2xxSuccessful(),
                        jsonPath(JSON_PATH, notNullValue())
                ));
    }

    public ResultActions createPATCHRequest(String url, String data, String revision) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.patch(url)
                        .header(ACCEPT, APPLICATION_JSON)
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .content(data))
                .andExpect(ResultMatcher.matchAll(
                        result -> System.out.println(result.getResponse().getContentAsString()),
                        status().is(HttpStatus.OK.value()),
                        jsonPath(JSON_PATH, notNullValue()),
                        status().is2xxSuccessful()
                ));
    }
}
