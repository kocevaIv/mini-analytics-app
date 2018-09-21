package com.brandwatch.ivanatwitterapp.api.services;

import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.MentionID;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoMentionRepository;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MentionServiceTest {

    @InjectMocks
    private MentionService mentionService;

    @Mock
    private MongoQueryRepository mongoQueryRepository;

    @Mock
    private MongoMentionRepository mongoMentionRepository;

    @Test(expected = QueryDoesNotExistException.class)
    public void givenQueryDoesNotExist_thenThrowException() {
        long queryId = 9;
        when(mongoQueryRepository.findQueryById(anyLong())).thenReturn(null);
        mentionService.getMentionsByQueryId(queryId);
    }

    @Test
    public void findMentionsByQueryId() {
        List<Mention> mentions = new ArrayList<Mention>();
        Mention mention = new Mention(new MentionID(2803083, 5), "", "", "", null);
        mentions.add(mention);
        when(mongoQueryRepository.findQueryById(anyLong())).thenReturn(new TwitterQuery(5, "apple"));
        when(mongoMentionRepository.findMentionsByQueryId(5)).thenReturn(mentions);
        assertEquals(mentions, mentionService.getMentionsByQueryId(5));
    }
}
