package com.ecommerceapi.ecommerceapi.controller;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapi.ecommerceapi.entities.Product;
import com.ecommerceapi.ecommerceapi.service.entitieservice.ProductService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeController {
private final ProductService repository;
    @GetMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
public Callable<List<Product>> getAllProducts () {
    return () -> repository.getAll();
}
}