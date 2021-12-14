package com.example.documentmanagement.controller;

import com.example.documentmanagement.AbstractBase;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class ArticleTest extends AbstractBase {
    private static final String DATA_ARTICLE_CREATE = "article_create_request.json";
    private static final String DATA_ARTICLE_UPDATE = "article_update_request.json";

    @Test
    void articleCreateTest() throws Exception {
        String articleCreateRequest = loadJsonData(DATA_ARTICLE_CREATE);
        mvc.perform(MockMvcRequestBuilders.post(ARTICLE_URL)
                        .header(HttpHeaders.ACCEPT, APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                        .content(articleCreateRequest))
                .andExpect(ResultMatcher.matchAll(
                        result -> log.info(result.getResponse().getContentAsString()),
                        status().is(HttpStatus.CREATED.value()),
                        header().exists(CONTENT_TYPE),
                        jsonPath(JSON_PATH, notNullValue()),
                        jsonPath(JSON_PATH_DATA_ID).isNotEmpty(),
                        jsonPath(JSON_PATH_TITLE).isNotEmpty(),
                        jsonPath(JSON_PATH_AUTHOR).isNotEmpty(),
                        jsonPath(JSON_PATH_ARTICLE_CONTENT).isNotEmpty()
                ));
    }

    @Test
    void articleDeleteTest() throws Exception {
        String articleCreateRequest = loadJsonData(DATA_ARTICLE_CREATE);
        mvc.perform(MockMvcRequestBuilders.post(ARTICLE_URL)
                        .header(HttpHeaders.ACCEPT, APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                        .content(articleCreateRequest))
                .andExpect(ResultMatcher.matchAll(
                        result -> log.info(result.getResponse().getContentAsString()),
                        status().is(HttpStatus.CREATED.value()),
                        header().exists(CONTENT_TYPE),
                        jsonPath(JSON_PATH, notNullValue()),
                        jsonPath(JSON_PATH_DATA_ID).isNotEmpty(),
                        jsonPath(JSON_PATH_TITLE).isNotEmpty(),
                        jsonPath(JSON_PATH_AUTHOR).isNotEmpty(),
                        jsonPath(JSON_PATH_ARTICLE_CONTENT).isNotEmpty()
                )).andDo(mvcResult -> {
                    Integer createdArticleId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.data.id");
                    mvc.perform(MockMvcRequestBuilders.delete(ARTICLE_URL + SLASH + createdArticleId)
                                    .header(HttpHeaders.ACCEPT, APPLICATION_JSON)
                                    .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON))
                            .andExpect(ResultMatcher.matchAll(
                                    status().is(HttpStatus.NO_CONTENT.value())
                            ));
                });
    }

    @Test
    void articleGetTest() throws Exception {
        String articleCreateRequest = loadJsonData(DATA_ARTICLE_CREATE);
        mvc.perform(MockMvcRequestBuilders.post(ARTICLE_URL)
                        .header(HttpHeaders.ACCEPT, APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                        .content(articleCreateRequest))
                .andExpect(ResultMatcher.matchAll(
                        result -> log.info(result.getResponse().getContentAsString()),
                        status().is(HttpStatus.CREATED.value()),
                        header().exists(CONTENT_TYPE),
                        jsonPath(JSON_PATH, notNullValue()),
                        jsonPath(JSON_PATH_DATA_ID).isNotEmpty(),
                        jsonPath(JSON_PATH_TITLE).isNotEmpty(),
                        jsonPath(JSON_PATH_AUTHOR).isNotEmpty(),
                        jsonPath(JSON_PATH_ARTICLE_CONTENT).isNotEmpty()
                )).andDo(mvcResult -> {
                    Integer createdArticleId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.data.id");
                    mvc.perform(MockMvcRequestBuilders.get(ARTICLE_URL + SLASH + createdArticleId))
                            .andExpect(ResultMatcher.matchAll(
                                    result -> log.info(result.getResponse().getContentAsString()),
                                    status().is(HttpStatus.OK.value()),
                                    header().exists(CONTENT_TYPE),
                                    jsonPath(JSON_PATH, notNullValue()),
                                    jsonPath(JSON_PATH_TITLE).isNotEmpty(),
                                    jsonPath(JSON_PATH_AUTHOR).isNotEmpty(),
                                    jsonPath(JSON_PATH_DATA_ID).isNotEmpty(),
                                    jsonPath(JSON_PATH_PUBLISH_DATE).isNotEmpty(),
                                    jsonPath(JSON_PATH_STAR_COUNT).isNotEmpty(),
                                    jsonPath(JSON_PATH_DATA_ID).isNotEmpty()
                            ));
                });
    }

    @Test
    void articleUpdateTest() throws Exception {
        String articleCreateRequest = loadJsonData(DATA_ARTICLE_CREATE);
        mvc.perform(MockMvcRequestBuilders.post(ARTICLE_URL)
                        .header(HttpHeaders.ACCEPT, APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                        .content(articleCreateRequest))
                .andExpect(ResultMatcher.matchAll(
                        result -> log.info(result.getResponse().getContentAsString()),
                        status().is(HttpStatus.CREATED.value()),
                        header().exists(CONTENT_TYPE),
                        jsonPath(JSON_PATH, notNullValue()),
                        jsonPath(JSON_PATH_DATA_ID).isNotEmpty(),
                        jsonPath(JSON_PATH_TITLE).isNotEmpty(),
                        jsonPath(JSON_PATH_AUTHOR).isNotEmpty(),
                        jsonPath(JSON_PATH_ARTICLE_CONTENT).isNotEmpty()
                )).andDo(mvcResult -> {
                    Integer createdArticleId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.data.id");
                    String updateRequest = loadJsonData(DATA_ARTICLE_UPDATE);
                    mvc.perform(MockMvcRequestBuilders.patch(ARTICLE_URL + SLASH + createdArticleId)
                                    .header(HttpHeaders.ACCEPT, APPLICATION_JSON)
                                    .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
                                    .content(updateRequest))
                            .andExpect(ResultMatcher.matchAll(
                                    result -> log.info(result.getResponse().getContentAsString()),
                                    status().is(HttpStatus.OK.value()),
                                    header().exists(CONTENT_TYPE),
                                    jsonPath(JSON_PATH, notNullValue()),
                                    jsonPath(JSON_PATH_DATA_ID).isNotEmpty(),
                                    jsonPath(JSON_PATH_TITLE, Matchers.equalTo(JsonPath.read(updateRequest, "title"))),
                                    jsonPath(JSON_PATH_AUTHOR, Matchers.equalTo(JsonPath.read(updateRequest, "author"))),
                                    jsonPath(JSON_PATH_ARTICLE_CONTENT, Matchers.equalTo(JsonPath.read(updateRequest, "articleContent")))
                            ));
                });
    }
}


