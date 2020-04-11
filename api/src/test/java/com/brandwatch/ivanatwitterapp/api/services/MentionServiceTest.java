package com.brandwatch.ivanatwitterapp.api.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;

import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.Query;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;
import com.brandwatch.ivanatwitterapp.mentionstorage.repository.MentionRepository;

@RunWith(MockitoJUnitRunner.class)
public class MentionServiceTest {

    @InjectMocks
    private MentionService mentionService;

    @Mock
    private MentionRepository mentionRepository;

    @Mock
    private QueryRepository queryRepository;

    @Test(expected = QueryDoesNotExistException.class)
    public void givenQueryDoesNotExist_thenThrowException() {
        long queryId = 9;
        mentionService.getMentionsByQueryId(queryId);
    }

    @Test
    public void findMentionsByQueryId() {
        Mention mention = new Mention.Builder()
                .withQueryId(5)
                .build();
        List<Mention> mentions = ImmutableList.of(mention);
        when(queryRepository.findQueryById(anyLong())).thenReturn(new Query("apple"));
        when(mentionRepository.findMentionsByQueryId(5)).thenReturn(mentions);
        assertEquals(mentions, mentionService.getMentionsByQueryId(5));
    }
}
