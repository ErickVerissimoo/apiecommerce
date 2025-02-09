package com.ecommerceapi.ecommerceapi.entities;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {
    

@OneToOne(cascade = CascadeType.ALL)

private Cart cart;

@PrePersist
public void generateCart(){
    if(cart ==null){
        Cart carro = new Cart();
        cart=carro;
        cart.setCostumer(this);


    }
}
public Customer(LoginRequest dto) {
    super(dto);
}
@Override
public boolean isAdmin() {
    
    return false;
}
}
