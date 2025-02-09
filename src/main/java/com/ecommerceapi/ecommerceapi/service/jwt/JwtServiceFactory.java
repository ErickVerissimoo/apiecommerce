package com.ecommerceapi.ecommerceapi.service.jwt;

import org.springframework.stereotype.Component;

import com.ecommerceapi.ecommerceapi.entities.User;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component

public class JwtServiceFactory  {
    private final AdminJwtService admin;
    private final CustomerJwtService customer;
    private final UserRepository repositry;
public JwtService getJwtService(Role role) {
  return role ==Role.ADMIN? admin: customer;
}

public JwtService getJwtService(User user){
return user.isAdmin()? admin : customer;
}
public JwtService getJwtService(String email){
return repositry.findByEmail(email).orElseThrow(EntityNotFoundException::new)
.isAdmin()? admin: customer;
}


}
