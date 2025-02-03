package com.ecommerceapi.ecommerceapi.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerceapi.ecommerceapi.entities.Admin;
import com.ecommerceapi.ecommerceapi.entities.Costumer;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.repositories.AdminRepository;
import com.ecommerceapi.ecommerceapi.repositories.CostumerRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
private final CostumerRepository repository;   
private final AdminRepository repositorio; 
private AtomicReference <Instant> atomic;
@Override
public String generateToken(Object target) {
    String token = null;
    atomic = new AtomicReference<Instant>(Instant.now());

    if(target instanceof Costumer){

        Costumer costumer= (Costumer) target;
  token = JWT.create().withIssuedAt(atomic.get())
        .withSubject(costumer.getEmail())
        .withClaim("id", costumer.getId())
        .withClaim("roles", Role.USER.name())
        .withExpiresAt(atomic.get().plus(15, ChronoUnit.MINUTES)).sign(Algorithm.HMAC512("minha chave mega secreta"));
    }
    if(target instanceof Admin){
        Admin admin = (Admin) target;
        token = JWT.create()
        .withSubject(admin.getEmail())
        .withExpiresAt(Instant.now()).withClaim("id", admin.getId())
        .withIssuedAt(Instant.now().plus(15, ChronoUnit.SECONDS)).withClaim("role", List.of(Role.ADMIN.name(), Role.USER.name())).sign(Algorithm.HMAC512("minha chave mega secreta"));
    }
    return token;
}
@Override
public Object getUser(String token) {
    var toke = JWT.decode(token);
    if (toke.getClaim("role").asList(String.class).contains("ADMIN"))
       return  repository.findAll().parallelStream().findFirst().filter(c -> c.getEmail().equals(toke.getSubject())).orElseThrow();
       
    
    else
    
    return repository.findByEmail(toke.getSubject()).orElseThrow();
}
@Override
public boolean isTokenExpired(String token) {
    if(atomic !=null)
    {
       return JWT.decode(token).getExpiresAtAsInstant().isBefore(atomic.get());
        }
    return false;
}
@Override
public boolean isTokenValid(String token) {

    return JWT.require(Algorithm.HMAC512("minha chave mega secreta")).build().verify(token).getSubject() !=null;
}
}
