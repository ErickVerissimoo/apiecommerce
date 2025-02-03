package com.ecommerceapi.ecommerceapi.entities;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(unique = true)
private String email;
private String password;
private Role role;
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
