package com.example.springdataDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springdataDemo.controller","com.example.springdataDemo.model",
		"com.example.springdataDemo.repository","com.example.springdataDemo.service","com.example.springdataDemo.utilities"})
public class SpringdataDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdataDemoApplication.class, args);
	}

}
