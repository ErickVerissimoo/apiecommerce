package com.ecommerceapi.ecommerceapi.entities.enums;

import lombok.Getter;

public enum StatusOrder {
PENDING(1), COMPLETED(2), CANCELED(3);
@Getter
private int status;

StatusOrder(int status) {
    this.status=status;
}
}
