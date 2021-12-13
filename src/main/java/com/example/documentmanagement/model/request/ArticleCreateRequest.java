package com.example.documentmanagement.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCreateRequest {
    private String title;
    private String author;
    private String articleContent;
}
