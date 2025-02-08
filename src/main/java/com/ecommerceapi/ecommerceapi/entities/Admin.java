package com.ecommerceapi.ecommerceapi.entities;

import org.springframework.beans.BeanUtils;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends User {

    public Admin(LoginAndRegisterDto dto) {
this();    
       setEmail(dto.email());
  setPassword(dto.password());
  
    }
 public Admin() {
    super();
  getRoles().add(Role.ADMIN);
 }
 public boolean isAdmin() {
    return true;
 }
 public Admin(Customer costumer) {
    BeanUtils.copyProperties(costumer, this);
 }
}
