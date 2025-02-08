package com.ecommerceapi.ecommerceapi.entities;

import java.util.ArrayList;
import java.util.List;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
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
    @Getter
private Long id;
@Column(unique = true)
private String email;
private String password;
@ElementCollection(targetClass = Role.class)
@Enumerated(EnumType.ORDINAL)
@CollectionTable(name = "roles")

private List<Role> roles = new ArrayList<>();
public User(LoginAndRegisterDto dto) {
    this();
    this.email=dto.email();
    this.password=dto.password();
}
public User() {
    roles.add(Role.USER);
}public abstract boolean isAdmin();

}

