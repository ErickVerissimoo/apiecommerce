package com.ecommerceapi.ecommerceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerceapi.ecommerceapi.repositories.CostumerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Configuration
public class SecurityConfig  {
    private final CostumerRepository repository;
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
return http.csrf(c -> c.disable())
.authorizeHttpRequests(c -> c.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.NEVER)).oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
.build();
}
@Bean
public PasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
}
public UserDetailsService service(){
    return email -> repository.findByEmail(email)
    .map(user -> User
    .withUsername(user.getEmail()).password(user.getPassword()).build()).orElseThrow(EntityNotFoundException::new);


}
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
return configuration.getAuthenticationManager();
}

}
