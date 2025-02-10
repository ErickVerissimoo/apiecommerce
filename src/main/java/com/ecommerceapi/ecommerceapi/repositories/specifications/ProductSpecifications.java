package com.ecommerceapi.ecommerceapi.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerceapi.ecommerceapi.entities.Product;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductSpecifications {
public Specification<Product> menorQue(Double price) {
    return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
}
public Specification<Product> maiorQue(Double price) {
    return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
}
public Specification<Product> entre(Double x, Double y) {
    return (root, query, cb) -> cb.between(root.get("price"), x, y);
}
}

