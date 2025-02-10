package com.ecommerceapi.ecommerceapi.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.ecommerceapi.ecommerceapi.entities.enums.StatusOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "pedido")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(insertable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private Customer customer;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,CascadeType.REMOVE}, orphanRemoval = true
    ,mappedBy = "order")

private Set<OrderItem> itens;
@Enumerated(EnumType.ORDINAL)
private StatusOrder status;
@PrePersist
public void setup(){
    status = StatusOrder.PENDING;
}
public Order() {
    itens = new HashSet<>();
}


}
