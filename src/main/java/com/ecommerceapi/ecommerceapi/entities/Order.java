package com.ecommerceapi.ecommerceapi.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="pedido")
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(insertable = false)
private LocalDateTime created;
@ManyToOne
@JoinColumn(name = "cart_id")
private Cart cart;
@OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
, orphanRemoval = true)
private List<Product> products;
public Double getTotalPrice(){
return products.stream().mapToDouble(c -> c.getPrice().doubleValue()).sum();
}

@PrePersist
public void setup(){
    created = LocalDateTime.now();
}


}
