package com.ecommerceapi.ecommerceapi.interfaces;

public interface JwtService {
    
String generateToken(String email);
String extractEmail(String email) throws IllegalArgumentException;
boolean isTokenValid(String token);
boolean isTokenExpired(String token);
}
