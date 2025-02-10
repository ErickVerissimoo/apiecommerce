package com.ecommerceapi.ecommerceapi.service.entitieservice;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.ecommerceapi.ecommerceapi.entities.Product;
import com.ecommerceapi.ecommerceapi.repositories.ProductRepository;
import static com.ecommerceapi.ecommerceapi.repositories.specifications.ProductSpecifications.*;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ProductService {
private final ProductRepository repository;
public List<Product> getAll() {
    return repository.findAll();
}
public List<Product> findAllLessThan(Double price){
return repository.findAll(menorQue(price));
}
public List<Product> findAllGreaterThan(Double price){
return repository.findAll(maiorQue(price));
}
public List<Product> findAllBetween(Double x, Double y){
    return repository.findAll(entre(x, y));
    }
    public List<Product> findAllLike(String name){
Product product = new Product();
product.setName(name);
Example<Product> example = Example.of(product, ExampleMatcher.matchingAll().withIgnoreCase()
.withStringMatcher(StringMatcher.CONTAINING));
return repository.findAll(example);


    }
    
}
