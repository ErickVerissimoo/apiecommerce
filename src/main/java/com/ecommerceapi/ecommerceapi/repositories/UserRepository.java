package com.ecommerceapi.ecommerceapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceapi.ecommerceapi.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
boolean existsByEmail(String email);
Optional<User> findByEmail(String email);
boolean existsByEmailAndPassword(String email, String password);
}
