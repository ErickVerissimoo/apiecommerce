package com.ecommerceapi.ecommerceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerceapi.ecommerceapi.repositories.CostumerRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Configuration
@AuthorizeReturnObject
public class SecurityConfig  {
    private final CostumerRepository repository;
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    var conf = OAuth2AuthorizationServerConfigurer.authorizationServer();
    conf.configure(http);
    
return http.csrf(c -> c.disable())
.authorizeHttpRequests(c -> c.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.NEVER))
.build();
}
}
