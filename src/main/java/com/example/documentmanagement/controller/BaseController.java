package com.example.documentmanagement.controller;


import com.example.documentmanagement.model.DataResponse;
import com.example.documentmanagement.model.ErrorResponse;
import com.example.documentmanagement.model.Response;
import com.example.documentmanagement.model.ResponseBuilder;

import java.util.List;

public abstract class BaseController {

    public <T> Response<DataResponse<T>> respond(List<T> items) {
        return ResponseBuilder.build(items);
    }

    public <T> Response<DataResponse<T>> respond(List<T> items, int page, int size, Long totalSize) {
        return ResponseBuilder.build(items, page, size, totalSize);
    }

    protected <T> Response<T> respond(T item) {
        return ResponseBuilder.build(item);
    }

    protected Response<ErrorResponse> respond(ErrorResponse errorResponse) {
        return ResponseBuilder.build(errorResponse);
    }
}
