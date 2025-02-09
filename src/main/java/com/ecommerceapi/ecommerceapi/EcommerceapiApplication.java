package com.ecommerceapi.ecommerceapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerceapi.ecommerceapi.entities.Admin;
import com.ecommerceapi.ecommerceapi.repositories.AdminRepository;

@SpringBootApplication
public class EcommerceapiApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(EcommerceapiApplication.class);
		application.run(args);

}
@Bean
public CommandLineRunner runner(AdminRepository repository, PasswordEncoder encode){
	return c ->{ var admin = new Admin();
	admin.setEmail("erick@gmail.com");
	admin.setPassword(encode.encode("erick123"));
	repository.saveAndFlush(admin);
};
}
}