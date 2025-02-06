package com.ecommerceapi.ecommerceapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerceapi.ecommerceapi.repositories.CostumerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CostumerRepository repository;
    private final JdbcOperations operations;

    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        return http.csrf(c -> c.disable())
        .httpBasic(c -> c.disable())
        .formLogin(c -> c.disable())
        .authorizeHttpRequests(c -> c.requestMatchers("/auth/**", "/public/**").permitAll()
        .anyRequest().authenticated())
        .build();
    }
   
}
