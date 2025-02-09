package com.ecommerceapi.ecommerceapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
		
	}

}
