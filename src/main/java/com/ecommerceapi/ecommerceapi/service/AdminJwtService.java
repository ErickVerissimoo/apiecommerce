package com.ecommerceapi.ecommerceapi.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerceapi.ecommerceapi.entities.User;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

import lombok.Setter;
@Component
public class AdminJwtService implements JwtService{
    @Setter
    private User user;

    private static final Algorithm algorithm = Algorithm.HMAC512("Minha chave blaster e ultra secreta".getBytes());
    private AtomicReference<Instant> instant;
    @Override
public String extractEmail(String email) throws IllegalArgumentException {
    
    return null;
}
@Override
public String generateToken(String email) {
    if(!email.contains("admin")){
throw new IllegalArgumentException("Email inválido");
    }

instant.set(Instant.now());
    return JWT.create().withClaim("role", List.of(Role.ADMIN.name(), Role.USER.name()))
    .withSubject(email).withExpiresAt(instant.get().plus(15, ChronoUnit.MINUTES))
    .withIssuedAt(instant.get())
    .withIssuer("admin jwt service").sign(algorithm);
}
@Override
public boolean isTokenExpired(String token) {
    // TODO Auto-generated method stub
    return false;
}
@Override
public boolean isTokenValid(String token) {
    // TODO Auto-generated method stub
    return false;
}
}
