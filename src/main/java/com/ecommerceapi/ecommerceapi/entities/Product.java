package com.ecommerceapi.ecommerceapi.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = {
    "name"
}))
@Entity
@Getter
@Setter
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;
private String description;
private BigDecimal price; 
@ManyToOne
@JoinColumn(name = "order_id")
private Order order;
}
