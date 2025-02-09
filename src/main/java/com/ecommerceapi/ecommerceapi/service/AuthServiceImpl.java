package com.ecommerceapi.ecommerceapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;
import com.ecommerceapi.ecommerceapi.entities.Customer;
import com.ecommerceapi.ecommerceapi.interfaces.AuthService;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;
import com.ecommerceapi.ecommerceapi.service.jwt.JwtServiceFactory;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
  private final JwtServiceFactory factory;
  private final PasswordEncoder encoder;
    @Override
public String login(LoginRequest dto) {

var user = repository.findByEmail(dto.email()).orElseThrow(EntityNotFoundException::new);
if(!encoder.matches(dto.password(), user.getPassword())){
throw new EntityNotFoundException();
}


  JwtService jwt = factory.getJwtService(user.getEmail());
  
  return jwt.generateToken(user.getEmail());
 
}
@Override
public void register(LoginRequest user) {
    if(repository.existsByEmail(user.email())){
throw new EntityExistsException();
    }
   Customer customer = new Customer(user);
   customer.setPassword(encoder.encode(user.password()));
   repository.saveAndFlush(customer);
    
}
}
