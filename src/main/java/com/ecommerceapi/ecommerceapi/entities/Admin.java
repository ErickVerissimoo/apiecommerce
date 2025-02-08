package com.ecommerceapi.ecommerceapi.entities;

import org.springframework.beans.BeanUtils;

import com.ecommerceapi.ecommerceapi.dto.LoginAndRegisterDto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends User {

    public Admin(LoginAndRegisterDto dto) {
        super(dto);
    }
 public Admin() {
  
 }
 public Admin(Customer costumer) {
    BeanUtils.copyProperties(costumer, this);
 }
}
