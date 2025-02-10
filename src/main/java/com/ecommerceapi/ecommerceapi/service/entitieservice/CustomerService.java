package com.ecommerceapi.ecommerceapi.service.entitieservice;

import org.springframework.stereotype.Service;

import com.ecommerceapi.ecommerceapi.dto.OrderItemDto;
import com.ecommerceapi.ecommerceapi.entities.Order;
import com.ecommerceapi.ecommerceapi.entities.OrderItem;
import com.ecommerceapi.ecommerceapi.repositories.CostumerRepository;
import com.ecommerceapi.ecommerceapi.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class CustomerService {
private final CostumerRepository repo;
private final ProductRepository repository;
public void createOrder(OrderItemDto dto, String email) {
    var customer = repo.findByEmail(email).orElseThrow();

    Order order = new Order();
    order.setCustomer(customer);  

    var product = repository.findByName(dto.nameproduct())
            .orElseThrow(EntityNotFoundException::new);

    OrderItem orderItem = new OrderItem();
    orderItem.setItemsQuantidade(dto.quantidade());
    orderItem.setProduct(product);
    orderItem.setOrder(order);

    order.getItens().add(orderItem); 

    customer.getOrders().add(order); 

    repo.saveAndFlush(customer); 
}
}
