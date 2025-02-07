package com.ecommerceapi.ecommerceapi.service;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

public class JwtServiceFactory {
public static JwtService getJwtService(Role role) {
    
    return role == Role.USER? new CostumerJwtService(): new AdminJwtService();
}
}
