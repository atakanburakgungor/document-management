package com.example.documentmanagement.model.request;

import lombok.Data;

@Data
public class AuthLoginRequest {
    private String username;
    private String password;
}
