package com.ecommerceapi.ecommerceapi.service.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
    
@Service
public class CustomerJwtService extends AbstractJwtService {

public CustomerJwtService(JwtEncoder encoder, JwtDecoder decoder) {
    super(encoder, decoder);
}
@Override
public String generateToken(Authentication authentication) {
  
    String token =super.generateToken(authentication);
    Jwt jwt = decoder.decode(token);

    var jw =JwtClaimsSet.builder().claims(c -> c.putAll(jwt.getClaims())).issuedAt(Instant.now()).expiresAt(
        Instant.now().plus(15, ChronoUnit.MINUTES)
    ).issuer("customer jwt").build();
    return encoder.encode(JwtEncoderParameters.from(jw)).getTokenValue();
}
@Override
public String extractEmail(String token) {
    return decoder.decode(token).getSubject();
}
}
