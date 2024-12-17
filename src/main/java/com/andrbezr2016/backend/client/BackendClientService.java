package com.andrbezr2016.backend.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan("com.andrbezr2016.backend.client.config")
@SpringBootApplication
public class BackendClientService {

    public static void main(String[] args) {
        SpringApplication.run(BackendClientService.class, args);
    }
}
