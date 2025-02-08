package com.ecommerceapi.ecommerceapi.entities;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;

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
public class Costumer extends User {
    
public Costumer(LoginAndRegisterDto dto) {
        super(dto);
    }

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
}
