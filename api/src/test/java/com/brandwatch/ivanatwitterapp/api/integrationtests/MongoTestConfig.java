package com.brandwatch.ivanatwitterapp.api.integrationtests;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.configuration.ShutdownStrategy;
import com.palantir.docker.compose.connection.DockerPort;

@Configuration
public class MongoTestConfig extends AbstractMongoConfiguration {

    @Bean(initMethod = "populateTestDatabase")
    public DatabaseFixtures databaseManager() {
        return new DatabaseFixtures();
    }

    @Bean(initMethod = "before", destroyMethod = "after")
    public DockerComposeRule dockerComposeRule() throws IOException, InterruptedException {
        DockerComposeRule dockerComposeRule = DockerComposeRule.builder()
                .file("src/test/resources/docker-compose.yml")
                .shutdownStrategy(ShutdownStrategy.GRACEFUL)
                .build();
        return dockerComposeRule;
    }

    @Override
    protected String getDatabaseName() {
        return "testTweets";
    }


    @Override
    @Bean
    public MongoClient mongoClient() {
        DockerPort port = null;
        try {
            port = dockerComposeRule().containers().container("mongo").port(27017);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new MongoClient("localhost", port.getExternalPort());
    }
}
