package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Thread.currentThread().setName("eduBD-Project");
        SpringApplication.run(Application.class, args);
    }

}
