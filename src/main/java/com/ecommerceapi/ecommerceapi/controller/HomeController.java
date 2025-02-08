package com.ecommerceapi.ecommerceapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapi.ecommerceapi.entities.User;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping
public void name(User user) {
    System.out.println(user.getEmail());
}
}
