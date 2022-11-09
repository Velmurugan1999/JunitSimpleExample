package com.junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class JunitExample2Application {
	public Logger logger=LoggerFactory.getLogger("junit");
	public static void main(String[] args) {
		SpringApplication.run(JunitExample2Application.class, args);
	}

}
