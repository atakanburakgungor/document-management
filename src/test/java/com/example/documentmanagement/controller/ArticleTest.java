package com.example.documentmanagement.controller;

import com.example.documentmanagement.AbstractBase;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class ArticleTest extends AbstractBase {
    private static final String DATA_CREATE = "article_create_request.json";

    @Test
    void articleCreateTest() throws Exception {
        String createdJsonData = loadJsonData(DATA_CREATE);
        String id = UUID.randomUUID().toString();
        String finalData = JsonPath.parse(createdJsonData)
                .set(JSON_PATH_ID, id).jsonString();
        createPOSTRequest(ARTICLE_URL, finalData).andExpect(ResultMatcher.matchAll(
                status().is(HttpStatus.CREATED.value()),
                header().exists(CONTENT_TYPE),
                jsonPath(JSON_PATH, notNullValue()),
                jsonPath(JSON_PATH_DATA_ID).isNotEmpty(),
                jsonPath(JSON_PATH_AUTHOR).isNotEmpty(),
                jsonPath(JSON_PATH_TITLE).isNotEmpty(),
                jsonPath(JSON_PATH_ARTICLE_CONTENT).isString(),
                jsonPath(JSON_PATH_DATA_ID, Matchers.equalTo(id))
        ));
    }
}
