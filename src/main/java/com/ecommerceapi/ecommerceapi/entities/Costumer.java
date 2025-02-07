package com.ecommerceapi.ecommerceapi.entities;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
public class Costumer extends User {
    
public Costumer(LoginAndRegisterDto dto) {
        super(dto);
    }

@OneToOne
@JoinColumn(name = "cart_id")
private Cart cart;

@PrePersist
public void generateCart(){
    if(cart ==null){
        Cart carro = new Cart();
        cart=carro;
        cart.setCostumer(this);


    }
}
}
