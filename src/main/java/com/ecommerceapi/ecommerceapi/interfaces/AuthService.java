package com.ecommerceapi.ecommerceapi.interfaces;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
import com.ecommerceapi.ecommerceapi.entities.User;

public interface AuthService {
String login(LoginAndRegisterDto dto);
void register(User user);
}
