package com.brandwatch.ivanatwitterapp.api.integrationtests;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.ivanatwitterapp.api.ApiApplication;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.repositories.MentionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class, MongoTestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class MentionIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private static String BASE_URI = "/mentions";

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final LocalDateTime START_DATE = LocalDateTime.of(2018,
            Month.SEPTEMBER,
            22,
            10, 30);

    private final LocalDateTime END_DATE = LocalDateTime.of(2018,
            Month.SEPTEMBER,
            23,
            13, 30);

    private static final int LIMIT = 1;

    @Autowired
    private MentionRepository mentionRepository;

    @Test
    public void testReadMentions() throws IOException {
        String fullUrl = createURLWithPort() + "?limit=" + LIMIT
                + "&startDate=" + START_DATE
                + "&endDate=" + END_DATE;
        String response = testRestTemplate.getForObject(fullUrl, String.class);
        List<Mention> actualMentions = objectMapper.readValue(response, new TypeReference<List<Mention>>() {
        });
        List<Mention> expectedMentions = mentionRepository.readMentions(LIMIT,
                START_DATE,
                END_DATE);
        Assert.assertNotEquals("No mentions in database", 0, expectedMentions.size());
        Assert.assertEquals(expectedMentions, actualMentions);
    }

    private String createURLWithPort() {
        return "http://localhost:" + port + BASE_URI;
    }
}
