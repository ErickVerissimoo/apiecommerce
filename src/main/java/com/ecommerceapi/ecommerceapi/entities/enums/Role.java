package com.ecommerceapi.ecommerceapi.entities.enums;

import lombok.Getter;

public enum Role {
USER(1),ADMIN(2);
@Getter
public int role;
Role(int role) {
    this.role=role;
}

}