package com.ecommerceapi.ecommerceapi.service.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public abstract class AbstractJwtService implements JwtService {
    protected final JwtEncoder encoder;
    protected final JwtDecoder decoder;
@Override
public String extractEmail(String token) {

    return decoder.decode(token).getSubject();
}
@Override
public String generateToken(Authentication authentication) {
    JwtClaimsSet set = JwtClaimsSet.builder()
    .subject(authentication.getPrincipal().toString()).claim("role", Role.USER.toString())
    .build();
    
    return encoder.encode(JwtEncoderParameters.from(set)).getTokenValue();
}

}
