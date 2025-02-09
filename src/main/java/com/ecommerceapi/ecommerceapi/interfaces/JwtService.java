package com.ecommerceapi.ecommerceapi.interfaces;

import org.springframework.security.core.Authentication;

public interface JwtService {
String generateToken(Authentication authentication);
String extractEmail(String token );

}
