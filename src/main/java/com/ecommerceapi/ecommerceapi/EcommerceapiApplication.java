package com.ecommerceapi.ecommerceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceapiApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(EcommerceapiApplication.class);
		application.run(args);

}
}