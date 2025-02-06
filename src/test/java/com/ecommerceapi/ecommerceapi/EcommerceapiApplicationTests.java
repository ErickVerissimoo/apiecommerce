package com.ecommerceapi.ecommerceapi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ecommerceapi.ecommerceapi.entities.Admin;
import com.ecommerceapi.ecommerceapi.entities.enums.Role;
import com.ecommerceapi.ecommerceapi.repositories.AdminRepository;
import com.ecommerceapi.ecommerceapi.repositories.CostumerRepository;
import com.ecommerceapi.ecommerceapi.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EcommerceapiApplicationTests {
	@Autowired
	CostumerRepository repository1;
	@Autowired
	AdminRepository repository2;
	@Autowired
	UserRepository repository3;
	@Test
	void contextLoads() {
		Admin admin = new Admin();
		admin.setEmail("erickverissimodasilva144@gmail.com");
		admin.setPassword("erick12345");
		admin.setRoles(List.of(Role.ADMIN, Role.USER));
		repository2.saveAndFlush(admin);
		System.out.println(repository3.existsByEmail(admin.getEmail()));
	}

}
