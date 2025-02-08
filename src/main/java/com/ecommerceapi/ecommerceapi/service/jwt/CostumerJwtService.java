package com.ecommerceapi.ecommerceapi.service.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

public class CostumerJwtService implements JwtService {
    private AtomicReference<Instant> instant;
    private static final Algorithm algorithm = Algorithm.HMAC512("Minha chave blaster e ultra secreta".getBytes());
 
 
    @Override
    public String generateToken(String email) {
        instant = new AtomicReference<>();
        instant.set(Instant.now());
        return JWT.create()
        .withSubject(email).withIssuedAt(instant.get())
        .withClaim("role", List.of(Role.USER.toString()))
        .withIssuer("jwt service").withExpiresAt(instant.get().plus(15, ChronoUnit.MINUTES))
        .sign(algorithm);
    }

@Override
public boolean isTokenExpired(String token) {
    return JWT.decode(token).getExpiresAtAsInstant().isBefore(instant.get()) || token.isEmpty();
}
@Override
public boolean isTokenValid(String token) {

    return JWT.require(algorithm)
    .withIssuer("jwt service")
    .build().verify(token) != null&& isTokenExpired(token);
}
@Override
public String extractEmail(String token)throws IllegalArgumentException {
   return JWT.require(algorithm)
   .build()
   .verify(token)
   .getSubject();
    
}
}