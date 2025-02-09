package com.ecommerceapi.ecommerceapi.service.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminJwtService implements JwtService {
  private final JwtEncoder encoder;
  private final JwtDecoder decoder;
@Override
public String extractEmail(String token) {

    return decoder.decode(token).getSubject();
}
@Override
public String generateToken(Authentication authentication) {
   
   String claims = Stream.of(Role.USER.toString(), Role.ADMIN.toString())
   .collect(Collectors.joining(","));
    JwtClaimsSet set = JwtClaimsSet
    .builder().subject(authentication.getPrincipal().toString())
    .claim("role", claims)
    .issuedAt(Instant.now())
    .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
    .issuer("customer jwt")
    .build();
    return encoder.encode(JwtEncoderParameters.from(set)).getTokenValue();
    
}

@Override
public String generateToken(String email) {
  
    
     JwtClaimsSet set = JwtClaimsSet
     .builder().subject(email)
     .claim("role", new String[]{Role.USER.toString(), Role.ADMIN.toString()})
     .issuedAt(Instant.now())
     .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
     .issuer("customer jwt")
     .build();
     return encoder.encode(JwtEncoderParameters.from(set)).getTokenValue();
}
}
