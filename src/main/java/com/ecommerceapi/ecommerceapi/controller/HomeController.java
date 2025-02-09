package com.ecommerceapi.ecommerceapi.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    @Secured("ADMIN")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
public void name(Principal principal) {
    System.out.println(principal.getName());
}
}
