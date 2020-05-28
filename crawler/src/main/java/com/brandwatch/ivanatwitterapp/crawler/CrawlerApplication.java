package com.brandwatch.ivanatwitterapp.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.brandwatch.ivanatwitterapp.crawler", "com.brandwatch.ivanatwitterapp.common", "com.brandwatch.ivanatwitterapp.mentionstorage"})
public class CrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }
}
