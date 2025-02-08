package com.ecommerceapi.ecommerceapi.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@OneToOne(mappedBy = "cart")
private Customer costumer;
@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval = true
,mappedBy = "cart")
private Set<Order> orders;
}
