package com.ecommerceapi.ecommerceapi.entities;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
private Long id;
private String email;
private String password;
public Role roles;
}
