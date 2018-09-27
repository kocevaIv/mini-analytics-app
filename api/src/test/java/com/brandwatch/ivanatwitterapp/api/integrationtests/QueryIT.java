package com.brandwatch.ivanatwitterapp.api.integrationtests;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.brandwatch.ivanatwitterapp.api.ApiApplication;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class QueryIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private static final String BASE_URI = "/queries";

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String HASHTAG = "mcdonalds";

    private static final String UPDATED_HASHTAG = "java";

    @Autowired
    private QueryRepository queryRepository;

    @Before
    public void init(){
        RestTemplate restTemplate = testRestTemplate.getRestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(httpRequestFactory);
    }

    @Test
    public void testCreateQuery() throws IOException {
        final String requestBody = "hashtag=mcdonalds";
        String url = createURLWithPort();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity(requestBody, headers);
        String response = testRestTemplate.postForObject(url, entity, String.class);
        TwitterQuery actualQuery = objectMapper.readValue(response, TwitterQuery.class);
        Assert.assertEquals(HASHTAG, actualQuery.getHashtag());
    }

    @Test
    public void testDeleteQuery() {
        long queryId = 3;
        TwitterQuery twitterQuery = new TwitterQuery();
        twitterQuery.setHashtag(HASHTAG);
        queryRepository.saveQuery(twitterQuery);
        String fullUrl = createURLWithPort() + "/" + queryId;
        testRestTemplate.delete(fullUrl);
        Assert.assertNull(queryRepository.findQueryById(queryId));
    }

    @Test
    public void testUpdateQuery() throws IOException {
        long queryId = 2;
        TwitterQuery expectedTwitterQuery = new TwitterQuery(queryId, UPDATED_HASHTAG);
        String fullUrl = createURLWithPort() + "/" + queryId + "?hashtag=" + UPDATED_HASHTAG;
        HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());
        ResponseEntity<String> response = testRestTemplate.exchange(fullUrl, HttpMethod.PATCH, entity, String.class);
        TwitterQuery actualTwitterQuery = objectMapper.readValue(response.getBody(), TwitterQuery.class);
        Assert.assertEquals(expectedTwitterQuery.getHashtag(), actualTwitterQuery.getHashtag());
    }

    private String createURLWithPort() {
        return "http://localhost:" + port + BASE_URI;
    }
}
