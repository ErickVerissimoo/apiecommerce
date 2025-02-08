package com.ecommerceapi.ecommerceapi.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {


@OneToOne(mappedBy = "cart")
@MapsId
@JoinColumn(name = "id") 
private Costumer costumer;
@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval = true
,mappedBy = "cart")
private Set<Order> orders;
}
