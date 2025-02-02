package com.ecommerceapi.ecommerceapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.ecommerceapi.ecommerceapi.entities.Costumer;



@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long>, JpaSpecificationExecutor<Costumer> {
Optional<Costumer> findByEmail(String email);
boolean existsByEmail(String email);
Streamable<Costumer> streamByEmail(String email);
}
