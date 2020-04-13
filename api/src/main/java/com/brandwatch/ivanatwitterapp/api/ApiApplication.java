package com.brandwatch.ivanatwitterapp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.brandwatch.ivanatwitterapp.api",
        "com.brandwatch.ivanatwitterapp.common",
        "com.brandwatch.ivanatwitterapp.mentionstorage.repository"})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
