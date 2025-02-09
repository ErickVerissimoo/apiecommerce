package com.ecommerceapi.ecommerceapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;
import com.ecommerceapi.ecommerceapi.interfaces.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final AuthService service;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest dto) {
        
    return  ResponseEntity.accepted()
    .header(HttpHeaders.AUTHORIZATION, service.login(dto)).build();
}
@PostMapping("/register")
public ResponseEntity<Void> register(@RequestBody LoginRequest dto) {
    service.register(dto);
    return ResponseEntity
    .noContent()
    .build();
}

}

