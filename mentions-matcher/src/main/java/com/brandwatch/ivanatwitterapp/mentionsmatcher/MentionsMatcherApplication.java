package com.brandwatch.ivanatwitterapp.mentionsmatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.brandwatch.ivanatwitterapp.common", "com.brandwatch.ivanatwitterapp.mentionsmatcher"})
@EnableScheduling
public class MentionsMatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(MentionsMatcherApplication.class, args);
    }
}
