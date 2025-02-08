package com.ecommerceapi.ecommerceapi.filter;

import java.util.Optional;

import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerceapi.ecommerceapi.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
@RequiredArgsConstructor
public class HandlerJwtAuthenticationFilter extends OncePerRequestFilter{
    private final UserRepository repository;
@Override
@SneakyThrows
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        {
            var op = Optional.ofNullable(request.getAttribute("token"));
            if(op.isPresent()){
                String email = request.getAttribute("token").toString();
                var user = repository.findByEmail(email);
                request.setAttribute("user", user);
                filterChain.doFilter(request, response);
             return;
            }
  filterChain.doFilter(request, response);
}
}
