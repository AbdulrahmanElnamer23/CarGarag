package com.car_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CarApiApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CarApiApplication.class);
        Environment env = app.run(args).getEnvironment();

        String port = env.getProperty("server.port", "8081");
        System.out.println("✅ Application starting on port: " + port);
    }
}

