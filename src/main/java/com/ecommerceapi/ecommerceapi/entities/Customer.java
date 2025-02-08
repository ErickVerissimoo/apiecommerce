package com.ecommerceapi.ecommerceapi.entities;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;

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
    
public Customer(LoginAndRegisterDto dto) {
    super(dto);
    getRoles().add(Role.USER);
      
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
@Override
public boolean isAdmin() {
    
    return false;
}
}
