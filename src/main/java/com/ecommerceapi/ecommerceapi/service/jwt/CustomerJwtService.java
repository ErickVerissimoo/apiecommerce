package com.ecommerceapi.ecommerceapi.service.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class CustomerJwtService implements JwtService {
private final JwtEncoder encoder;
private final JwtDecoder decoder;

@Override
public String generateToken(Authentication authentication) {
  JwtClaimsSet set = JwtClaimsSet
  .builder()
  .claim("role", Role.USER.toString()+",")
  .subject(authentication.getPrincipal().toString())
    .issuer("customer jwt service")
    .issuedAt(Instant.now())
    .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
    .build();
return encoder.encode(JwtEncoderParameters.from(set)).getTokenValue();    
}
@Override
public String extractEmail(String token) {
    return decoder.decode(token)
    .getSubject();
}
@Override
public String generateToken(String email) {
    

     JwtClaimsSet set = JwtClaimsSet
     .builder().subject(email)
     .claim("role", Role.USER.toString())
     .issuedAt(Instant.now())
     .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
     .issuer("customer jwt")
     .build();
     return encoder.encode(JwtEncoderParameters.from(set)).getTokenValue();
}
}
