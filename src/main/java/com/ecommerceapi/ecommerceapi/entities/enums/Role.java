package com.ecommerceapi.ecommerceapi.entities.enums;

import lombok.Getter;

public enum Role {
USER("USER"),ADMIN("ADMIN");
@Getter
public String role;
Role(String role) {
    this.role=role;
}

}