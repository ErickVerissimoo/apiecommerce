package com.ecommerceapi.ecommerceapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapi.ecommerceapi.util.CurrentUser;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
public void name(@CurrentUser String email) {
    System.out.println(email);
}
}
