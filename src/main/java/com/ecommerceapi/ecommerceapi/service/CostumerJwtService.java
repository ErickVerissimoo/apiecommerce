package com.ecommerceapi.ecommerceapi.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

public class CostumerJwtService implements JwtService {
    private AtomicReference<Instant> instant;
    private static final Algorithm algorithm = Algorithm.HMAC256("minha chave secreta e escondida");
 
 
    @Override
    public String generateToken(String email) {
        instant.set(Instant.now());
        return JWT.create()
        .withSubject(email).withIssuedAt(instant.get())
        .withIssuer("jwt service").withExpiresAt(instant.get().plus(15, ChronoUnit.MINUTES))
        .sign(algorithm);
    }

private String getEmail(String token) {
    
    return JWT.decode(token).getSubject();
}
@Override
public boolean isTokenExpired(String token) {
    return JWT.decode(token).getExpiresAtAsInstant().isBefore(instant.get()) || token.isEmpty();
}
@Override
public boolean isTokenValid(String token) {
    String email = getEmail(token);
    return JWT.require(algorithm).withSubject(email)
    .withIssuer("jwt service")
    .build().verify(token) != null && isTokenExpired(token);
}
@Override
public String extractEmail(String token)throws IllegalArgumentException {
    if(isTokenValid(token))
    return getEmail(token);
    throw new IllegalArgumentException("Token ilegal");
}
}
