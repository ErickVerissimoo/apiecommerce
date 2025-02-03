package com.ecommerceapi.ecommerceapi.interfaces;

public interface JwtService {
String generateToken(Object target);
Object getUser(String token);
boolean isTokenValid(String token);
boolean isTokenExpired(String token);
}
