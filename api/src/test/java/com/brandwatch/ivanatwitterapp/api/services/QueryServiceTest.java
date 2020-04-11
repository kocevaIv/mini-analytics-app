package com.brandwatch.ivanatwitterapp.api.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyQueryDefinition;

@RunWith(MockitoJUnitRunner.class)
public class QueryServiceTest {

    @InjectMocks
    private QueryService queryService;

    @Test(expected = EmptyQueryDefinition.class)
    public void givenHashTagIsEmpty_whenQueryIsCreated_thenThrowException() {
        String hashTag = "";
        queryService.createQuery(hashTag);
    }

    @Test(expected = EmptyQueryDefinition.class)
    public void givenHashTagIsEmpty_whenQueryIsUpdated_thenThrowException() {
        String hashtag = "";
        long id = 10;
        queryService.updateQueryDefinition(id, hashtag);
    }
}