package com.belhard.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ContextConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(ContextConfiguration.class, args);
    }
}