package com.ecommerceapi.ecommerceapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecommerceapi.ecommerceapi.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceapiApplicationTests {
	@Autowired
	MockMvc mvc;
	@Test
	@SneakyThrows
	void contextLoads() {
		ObjectMapper mapper = new ObjectMapper();
		LoginRequest dto = new LoginRequest("erickverissimo10@gmail", "erick123");

		String json = mapper.writeValueAsString(dto);
		
		mvc.perform(post(URI.create("/auth/register")).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

}
