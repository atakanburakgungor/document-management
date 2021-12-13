package com.example.documentmanagement.controller.api;

import com.example.documentmanagement.entity.Article;
import com.example.documentmanagement.model.DataResponse;
import com.example.documentmanagement.model.Response;
import com.example.documentmanagement.model.request.ArticleCreateRequest;
import com.example.documentmanagement.model.request.ArticleUpdateRequest;
import com.example.documentmanagement.model.response.ArticleResponse;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-11T16:44:54.163+03:00")
@Api(value = "article", description = "The Document Management Article API")
public interface ArticleApi {
    @ApiOperation(value = "Creates a Article", nickname = "createArticle", notes = "This operation creates a Article entity.", response = ArticleResponse.class, tags = {"article",})
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created", response = ArticleResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/article", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"}, method = RequestMethod.POST)
    ResponseEntity<Response<ArticleResponse>> createArticle(@ApiParam(value = "The Article to be created", required = true) @Valid @RequestBody ArticleCreateRequest Article);

    @ApiOperation(value = "Deletes a Article", nickname = "deleteArticle", notes = "This operation deletes a article entity.", tags = {"Article",})
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/article/{id}", produces = {"application/json;charset=utf-8"}, method = RequestMethod.DELETE)
    ResponseEntity<Response<Long>> deleteArticle(@ApiParam(value = "Identifier of the Article", required = true) @PathVariable("id") Long id);

    @ApiOperation(value = "List or find article objects", nickname = "listArticle", notes = "This operation list or find article entities", response = Article.class, responseContainer = "List", tags = {"article",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ArticleResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/article", produces = {"application/json;charset=utf-8"}, method = RequestMethod.GET)
    ResponseEntity<Response<DataResponse<ArticleResponse>>> listArticle(
            @QuerydslPredicate(root = Article.class) Predicate predicate, Pageable pageable);

    @ApiOperation(value = "Updates partially a Article", nickname = "patchArticle", notes = "This operation updates partially a Article entity.", response = Article.class, tags = {"Article",})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Updated", response = ArticleResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/article/{id}", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"}, method = RequestMethod.PATCH)
    ResponseEntity<Response<ArticleResponse>> patchArticle(
            @ApiParam(value = "Identifier of the Article", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "The Article to be updated", required = true) @Valid @RequestBody ArticleUpdateRequest articleUpdateRequest);


    @ApiOperation(value = "Retrieves a Article by ID", nickname = "retrieveArticle", notes = "This operation retrieves a Article entity. Attribute selection is enabled for all first level attributes.", response = Article.class, tags = {"Article",})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ArticleResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/article/{id}", produces = {"application/json;charset=utf-8"}, method = RequestMethod.GET)
    ResponseEntity<Response<ArticleResponse>> retrieveArticle(
            @ApiParam(value = "Identifier of the Article", required = true) @PathVariable("id") Long id);
}
