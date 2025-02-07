package com.ecommerceapi.ecommerceapi.entities;

import java.util.List;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User   {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
private Long id;
@Column(unique = true)
private String email;
private String password;

public List<Role> roles;
public User(LoginAndRegisterDto dto) {
    this.email=dto.email();
    this.password=dto.password();
}

}

