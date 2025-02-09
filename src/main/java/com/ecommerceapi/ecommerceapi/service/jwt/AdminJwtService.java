package com.ecommerceapi.ecommerceapi.service.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
@Service
public class AdminJwtService extends AbstractJwtService {
    public AdminJwtService(JwtEncoder encoder, JwtDecoder decoder) {
        super(encoder, decoder);
    }
@Override
public String extractEmail(String token) {

    return super.extractEmail(token);
}
@Override
public String generateToken(Authentication authentication) {
    
    String token= super.generateToken(authentication);
    Jwt jwt = decoder.decode(token);
    JwtClaimsSet set = 
    JwtClaimsSet.builder()
    .claims(c -> c.putAll(jwt.getClaims()))
    .issuedAt(Instant.now())
    .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
    .issuer("admin jwt service").build();
    return encoder.encode(JwtEncoderParameters.from(set)).getTokenValue();
}
}
