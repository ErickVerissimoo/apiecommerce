package com.ecommerceapi.ecommerceapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;
import com.ecommerceapi.ecommerceapi.entities.Customer;
import com.ecommerceapi.ecommerceapi.interfaces.AuthService;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;
import com.ecommerceapi.ecommerceapi.service.jwt.JwtServiceFactory;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final AuthenticationManager manager;
  private final JwtServiceFactory factory;
  private final PasswordEncoder encoder;
    @Override
public String login(LoginRequest dto) {
  Authentication auth = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
  JwtService jwt = factory.getJwtService(auth.getPrincipal().toString());
  
  return jwt.generateToken(auth);
 
}
@Override
public void register(LoginRequest user) {
    if(repository.existsByEmail(user.email())){
throw new EntityExistsException();
    }
   
   repository.saveAndFlush(new Customer(user));
    
}
}
