package com.ecommerceapi.ecommerceapi.entities;

import java.util.List;

import com.ecommerceapi.ecommerceapi.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User  {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
private Long id;
@Column(unique = true)
private String email;
private String password;
public List<Role> roles;
}
