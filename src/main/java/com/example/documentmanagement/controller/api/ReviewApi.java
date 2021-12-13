package com.example.documentmanagement.controller.api;

import com.example.documentmanagement.entity.Review;
import com.example.documentmanagement.model.DataResponse;
import com.example.documentmanagement.model.Response;
import com.example.documentmanagement.model.request.ReviewCreateRequest;
import com.example.documentmanagement.model.request.ReviewUpdateRequest;
import com.example.documentmanagement.model.response.ReviewResponse;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-11T16:44:54.163+03:00")
@Api(value = "review", description = "The Document Management Review API")
public interface ReviewApi {
    @ApiOperation(value = "Creates a Review", nickname = "createReview", notes = "This operation creates a review entity.", response = ReviewResponse.class, tags = {"review",})
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created", response = ReviewResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/review", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"}, method = RequestMethod.POST)
    ResponseEntity<Response<ReviewResponse>> createReview(@ApiParam(value = "The Review to be created", required = true) @Valid @RequestBody ReviewCreateRequest request);

    @ApiOperation(value = "Deletes a Review", nickname = "deleteReview", notes = "This operation deletes a Review entity.", tags = {"Review",})
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request", response = UUID.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/review/{id}", produces = {"application/json;charset=utf-8"}, method = RequestMethod.DELETE)
    ResponseEntity<Response<Long>> deleteReview(@ApiParam(value = "Identifier of the Review", required = true) @PathVariable("id") Long id);

    @ApiOperation(value = "List or find Review objects", nickname = "listReview", notes = "This operation list or find review entities", response = Review.class, responseContainer = "List", tags = {"Review",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ReviewResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/review", produces = {"application/json;charset=utf-8"}, method = RequestMethod.GET)
    ResponseEntity<Response<DataResponse<ReviewResponse>>> listReview(
            @QuerydslPredicate(root = Review.class) Predicate predicate, Pageable pageable,
            HttpServletResponse response, HttpServletRequest request);

    @ApiOperation(value = "Updates partially a Review", nickname = "patchReview", notes = "This operation updates partially a review entity.", response = Review.class, tags = {"review",})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Updated", response = ReviewResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/review/{id}", produces = {"application/json;charset=utf-8"}, consumes = {"application/json;charset=utf-8"}, method = RequestMethod.PATCH)
    ResponseEntity<Response<ReviewResponse>> updateReview(
            @ApiParam(value = "Identifier of the Review", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "The Review to be updated", required = true) @Valid @RequestBody ReviewUpdateRequest ReviewUpdateRequest);


    @ApiOperation(value = "Retrieves a Review by ID", nickname = "retrieveReview", notes = "This operation retrieves a Review entity. Attribute selection is enabled for all first level attributes.", response = Review.class, tags = {"Review",})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ReviewResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/review/{id}", produces = {"application/json;charset=utf-8"}, method = RequestMethod.GET)
    ResponseEntity<Response<ReviewResponse>> retrieveReview(
            @ApiParam(value = "Identifier of the Review", required = true) @PathVariable("id") Long id);
}
