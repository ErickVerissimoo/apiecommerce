package com.ecommerceapi.ecommerceapi.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerceapi.ecommerceapi.entities.User;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

public class JwtServiceFactory {
        private static final Algorithm algorithm = Algorithm.HMAC512("Minha chave blaster e ultra secreta".getBytes());

public static JwtService getJwtService(String token) {
    
    return !isAdmin(token)? new CostumerJwtService(): new AdminJwtService();
}
public static JwtService getJwtService(User user) {
    
    return !!user.isAdmin()? new CostumerJwtService(): new AdminJwtService();
}

private static boolean isAdmin(String token){
return JWT.require(algorithm)
.withClaimPresence("role")
.build().verify(token)
.getClaim("role").asList(String.class)
.contains(Role.ADMIN.name());
}
}
