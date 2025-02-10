package com.ecommerceapi.ecommerceapi.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapi.ecommerceapi.dto.OrderItemDto;
import com.ecommerceapi.ecommerceapi.service.entitieservice.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
private CustomerService customer;
public String addProductToCart(Principal principal, @RequestBody OrderItemDto dto ) {
   
    return null;
}
public static record orderwrapper(String email, String product,  int dtoq){}


}
