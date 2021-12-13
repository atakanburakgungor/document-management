package com.example.documentmanagement.controller;

import com.example.documentmanagement.exception.BusinessException;
import com.example.documentmanagement.model.request.AuthLoginRequest;
import com.example.documentmanagement.security.TokenAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class AuthenticationController {

    private TokenAuthenticationService tokenAuthenticationService;
    private AuthenticationManager authenticationManager;

    public AuthenticationController(TokenAuthenticationService tokenAuthenticationService, AuthenticationManager authenticationManager) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody AuthLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return ResponseEntity.ok(tokenAuthenticationService.generateToken(request.getUsername()));
        } catch (Exception e) {
            log.error("Error occured while authentication , details : ", e);
            throw new BusinessException("general.authentication.error.occurred");
        }
    }
}
