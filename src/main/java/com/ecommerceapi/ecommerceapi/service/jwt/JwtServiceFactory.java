package com.ecommerceapi.ecommerceapi.service.jwt;

import org.springframework.stereotype.Component;

import com.ecommerceapi.ecommerceapi.entities.User;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtServiceFactory {
    private final UserRepository repository;
        
public JwtService getJwtService(String email) {
    var isAdmin= repository.findByEmail(email).orElseThrow(EntityNotFoundException::new)
    .isAdmin();
    return isAdmin? new CostumerJwtService(): new AdminJwtService();
}
public JwtService getJwtService(User user) {
    
    return !!user.isAdmin()? new CostumerJwtService(): new AdminJwtService();
}

}
