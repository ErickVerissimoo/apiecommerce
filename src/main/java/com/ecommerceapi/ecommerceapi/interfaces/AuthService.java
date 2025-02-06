package com.ecommerceapi.ecommerceapi.interfaces;

import com.ecommerceapi.ecommerceapi.entities.User;

public interface AuthService {
String login(User user);
void register(User user);
}
