package com.ecommerceapi.ecommerceapi.entities;

import java.util.HashSet;
import java.util.Set;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends User {
    
@OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE})
private Set<Order> orders;
public Customer(LoginRequest dto) {
    super(dto);
}
public Customer() {
    orders = new HashSet<>();
}
@Override
public boolean isAdmin() {
    
    return false;
}
}
