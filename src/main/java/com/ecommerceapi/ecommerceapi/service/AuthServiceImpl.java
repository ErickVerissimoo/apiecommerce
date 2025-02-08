package com.ecommerceapi.ecommerceapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
import com.ecommerceapi.ecommerceapi.entities.User;
import com.ecommerceapi.ecommerceapi.interfaces.AuthService;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;
import com.ecommerceapi.ecommerceapi.service.jwt.JwtServiceFactory;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final AuthenticationManager manager;
    private final PasswordEncoder encoder;
    private final JwtServiceFactory factory;
@Override
public String login(LoginAndRegisterDto dto) {
    
    Authentication authentication = manager.authenticate( new UsernamePasswordAuthenticationToken(dto.email(),dto.password()));
  SecurityContextHolder.getContext().setAuthentication(authentication);
    
    JwtService service = factory.getJwtService(dto.email());
  return service.generateToken(dto.email());
 
}
@Override
public void register(User user) {
    if(repository.existsByEmail(user.getEmail())){
throw new EntityExistsException();
    }
    user.setPassword(encoder.encode(user.getPassword()));
   repository.saveAndFlush(user);
    
}
}
