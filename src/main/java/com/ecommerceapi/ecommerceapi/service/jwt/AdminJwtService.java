package com.ecommerceapi.ecommerceapi.service.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
@Component
public class AdminJwtService implements JwtService{
   

    private static final Algorithm algorithm = Algorithm.HMAC512("Minha chave blaster e ultra secreta".getBytes());
    private AtomicReference<Instant> instant;
    @Override
public String extractEmail(String token) throws IllegalArgumentException {
    
    return JWT.require(algorithm)
    .build().verify(token)
    .getSubject();
}
@Override
public String generateToken(String email) {
  instant = new AtomicReference<>();
instant.set(Instant.now());

    return JWT.create().withClaim("role", List.of(Role.ADMIN.toString(), Role.USER.toString()))
    .withSubject(email).withExpiresAt(instant.get().plus(15, ChronoUnit.MINUTES))
    .withIssuedAt(instant.get())
    .withIssuer("admin jwt service").sign(algorithm);
}
@Override
public boolean isTokenExpired(String token) {
    
    return JWT.decode(token)
    .getExpiresAtAsInstant().isBefore(instant.get());
}
@Override
public boolean isTokenValid(String token) {

    return JWT.require(algorithm)
    .build()
    .verify(token)
    != null && isTokenExpired(token);
}
}
