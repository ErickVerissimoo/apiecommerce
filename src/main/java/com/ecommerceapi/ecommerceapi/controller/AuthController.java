package com.ecommerceapi.ecommerceapi.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
import com.ecommerceapi.ecommerceapi.entities.Costumer;
import com.ecommerceapi.ecommerceapi.interfaces.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;
    
    @PostMapping("/login")
public ResponseEntity<Void> login(@RequestBody LoginAndRegisterDto dto,
HttpServletResponse response) {
    String token = service.login(dto);
    Cookie cookie = new Cookie("JWT_COOKIE", token);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    
    response.addCookie(cookie);
return ResponseEntity.accepted().build();

}
@PostMapping("/register")
public ResponseEntity<Void> register(@RequestBody LoginAndRegisterDto dto) {
    service.register(new Costumer(dto));
    return ResponseEntity.noContent().build();
}
@PostMapping("/logout")
public ResponseEntity<Void> name(HttpServletResponse response, HttpServletRequest request) {
    var cookie = Optional.ofNullable(WebUtils.getCookie(request, "JWT_COOKIE"));
    if( cookie.isPresent()){
Cookie cook = cookie.get();
    
    SecurityContextHolder.clearContext();
    cook.setMaxAge(0);
    cook.setPath("/");
    response.addCookie(cook);
    return ResponseEntity.accepted().build();
    }
return ResponseEntity.notFound().build();
}
}

