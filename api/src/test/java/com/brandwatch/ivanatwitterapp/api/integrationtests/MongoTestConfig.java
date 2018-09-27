package com.brandwatch.ivanatwitterapp.api.integrationtests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoTestConfig {

    @Bean(initMethod = "populateTestDatabase")
    public DatabaseFixtures databaseManager() {
        return new DatabaseFixtures();
    }
}
