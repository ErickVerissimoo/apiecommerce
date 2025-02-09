package com.ecommerceapi.ecommerceapi.interfaces;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;

public interface AuthService {
String login(LoginRequest dto);
void register(LoginRequest dto);
}
