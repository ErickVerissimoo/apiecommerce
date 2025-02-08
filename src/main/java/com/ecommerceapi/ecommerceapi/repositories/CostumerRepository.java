package com.ecommerceapi.ecommerceapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.ecommerceapi.ecommerceapi.entities.Customer;



@Repository
public interface CostumerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
Optional<Customer> findByEmail(String email);
boolean existsByEmail(String email);
Streamable<Customer> streamByEmail(String email);
}
