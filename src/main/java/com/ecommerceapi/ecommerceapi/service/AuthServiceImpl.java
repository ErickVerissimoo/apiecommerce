package com.ecommerceapi.ecommerceapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
import com.ecommerceapi.ecommerceapi.entities.User;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.AuthService;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final AuthenticationManager manager;
    
@Override
public String login(LoginAndRegisterDto dto) {
    var usor = repository.findByEmail(dto.email()).orElseThrow(EntityNotFoundException::new);
    Authentication authentication = manager.authenticate( new UsernamePasswordAuthenticationToken(usor.getEmail(), usor.getPassword()));
    
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return generateToken(usor);
}
@Override
public void register(User user) {
    if(repository.existsByEmail(user.getEmail())){
throw new EntityExistsException();
    }
   repository.saveAndFlush(user);
    
}

private String generateToken(User user){
JwtService service = JwtServiceFactory.getJwtService(Role.USER);
return service.generateToken(user.getEmail());
}
}
