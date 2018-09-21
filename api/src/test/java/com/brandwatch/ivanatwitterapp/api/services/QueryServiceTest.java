package com.brandwatch.ivanatwitterapp.api.services;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyHashTagException;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QueryServiceTest {

    @InjectMocks
    private QueryService queryService;

    @Mock
    private MongoQueryRepository mongoQueryRepository;

    @Test(expected = EmptyHashTagException.class)
    public void givenHashTagIsEmpty_whenQueryIsCreated_thenThrowException() {
        String hashTag = "";
        queryService.createQuery(hashTag);
    }

    @Test(expected = EmptyHashTagException.class)
    public void givenHashTagIsEmpty_whenQueryIsUpdated_thenThrowException() {
        String hashtag = "";
        long id = 10;
        queryService.updateQueryForHashTag(id, hashtag);
    }
}