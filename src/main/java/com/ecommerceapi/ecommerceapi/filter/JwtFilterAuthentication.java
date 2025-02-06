package com.ecommerceapi.ecommerceapi.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.ecommerceapi.ecommerceapi.service.JwtServiceFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class JwtFilterAuthentication extends OncePerRequestFilter {
private final JwtServiceFactory factory;
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            String token = Optional.of(WebUtils.getCookie(request, "Bearer").getValue()).orElse(null);
            if(token != null){

            }

    
    
}

}
