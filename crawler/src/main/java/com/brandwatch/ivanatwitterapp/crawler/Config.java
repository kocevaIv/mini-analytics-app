package com.brandwatch.ivanatwitterapp.crawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Configuration
@EnableScheduling
public class Config {

    @Bean
    public Twitter getTwitterInstance() {
        return TwitterFactory.getSingleton();
    }
}

