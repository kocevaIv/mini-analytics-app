package com.brandwatch.ivanatwitterapp.api.integrationtests;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.configuration.ShutdownStrategy;
import com.palantir.docker.compose.connection.DockerPort;

import com.brandwatch.ivanatwitterapp.common.repositories.MongoMentionRepository;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;

@Configuration
public class MongoTestConfig extends AbstractMongoConfiguration {

    @Bean(initMethod = "populateTestDatabase")
    public DatabaseFixtures databaseManager() {
        return new DatabaseFixtures();
    }



    @Bean(destroyMethod = "after")
    public DockerComposeRule dockerComposeRule() throws IOException, InterruptedException {
        DockerComposeRule dockerComposeRule = DockerComposeRule.builder()
                .file("src/test/resources/docker-compose.yml")
                .shutdownStrategy(ShutdownStrategy.GRACEFUL)
                .build();
          dockerComposeRule.before();
          return dockerComposeRule;
    }



   /* @Bean
    public MongoDbFactory mongoDbFactory(DockerComposeRule docker) throws Exception {

        return new SimpleMongoDbFactory(mongoClient, "testTweets");
    }

    public @Bean
    MongoTemplate mongoTemplate(MongoDbFactory factory) throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(factory);
        return mongoTemplate;
    }*/

    @Override
    protected String getDatabaseName() {
        return "testTweets";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        DockerPort port = dockerComposeRule().containers().container("mongo").port(27017);
        return  new MongoClient("localhost",port.getExternalPort());
    }

    @Bean
    public MongoMentionRepository mongoMentionRepository(){
        return new MongoMentionRepository();
    }
    @Bean
    public MongoQueryRepository mongoQueryRepository(){
        return new MongoQueryRepository();
    }

}
