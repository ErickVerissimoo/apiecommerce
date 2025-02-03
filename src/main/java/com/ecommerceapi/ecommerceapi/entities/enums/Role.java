package com.ecommerceapi.ecommerceapi.entities.enums;

public enum Role {
USER(1),ADMIN(2);
public int role;
Role(int role) {
    this.role=role;
}
}