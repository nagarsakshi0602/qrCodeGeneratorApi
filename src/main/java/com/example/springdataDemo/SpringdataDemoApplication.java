package com.example.springdataDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springdataDemo.controller", "com.example.springdataDemo.model",
        "com.example.springdataDemo.repository", "com.example.springdataDemo.service",
        "com.example.springdataDemo.utilities", "com.example.springdataDemo.model.response",
        "com.example.springdataDemo.view_controller"})

public class SpringdataDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataDemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false);
        return builder.sources(SpringdataDemoApplication.class);
    }
}
