package com.ecommerceapi.ecommerceapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = {
    "name"
}))
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;
private String description;
private Double price; 

}
