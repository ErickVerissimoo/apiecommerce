package com.ecommerceapi.ecommerceapi.util;

import com.auth0.jwt.JWT;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SimpleJwtDecoder {
public boolean isAdmin(String token){
return JWT.decode(token).getClaim("role")
.asList(String.class).contains(Role.ADMIN.name());
}
}
