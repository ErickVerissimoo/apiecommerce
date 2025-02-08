package com.ecommerceapi.ecommerceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerceapi.ecommerceapi.filter.HandlerJwtAuthenticationFilter;
import com.ecommerceapi.ecommerceapi.filter.JwtFilterAuthentication;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserRepository repository;
    private final JdbcOperations operations;
    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http.csrf(CsrfConfigurer::disable)
        .addFilterBefore(new JwtFilterAuthentication(), UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(new HandlerJwtAuthenticationFilter(repository) , JwtFilterAuthentication.class)
        .httpBasic(HttpBasicConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        
        .authorizeHttpRequests(c -> c.requestMatchers("/auth/**", "/public/**").permitAll()
        .anyRequest().authenticated())
        .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }
    @Bean
@SneakyThrows
    public AuthenticationManager manager(AuthenticationConfiguration conf){
        return conf.getAuthenticationManager();

    }
   
}
