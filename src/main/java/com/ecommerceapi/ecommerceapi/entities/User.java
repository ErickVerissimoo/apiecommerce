package com.ecommerceapi.ecommerceapi.entities;

import java.util.HashSet;
import java.util.Set;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User   {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    
private Long id;
@Column(unique = true)
private String email;
private String password;
@ElementCollection(targetClass = Role.class)
@Enumerated(EnumType.STRING)
@CollectionTable(name = "roles")

private Set<Role> roles;
public User(LoginRequest dto){
    this();
this.email=dto.email();
this.password=dto.password();
}
public User() {
    roles =  new HashSet<>();
    roles.add(Role.USER);
}public abstract boolean isAdmin();

}

