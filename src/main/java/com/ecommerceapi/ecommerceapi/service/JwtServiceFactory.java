package com.ecommerceapi.ecommerceapi.service;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.interfaces.JwtService;

public class JwtServiceFactory {
public static JwtService getJwtService(String token) {
    
    if(user.getRoles().contains(Role.ADMIN)){
return new AdminJwtService();
    }
    return new CostumerJwtService(); 
}
}
