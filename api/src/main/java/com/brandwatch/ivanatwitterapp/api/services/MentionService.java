package com.brandwatch.ivanatwitterapp.api.services;


import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoMentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MentionService {

    @Autowired
    private MongoMentionRepository mongoMentionRepository;

    @Autowired
    private MongoQueryRepository mongoQueryRepository;

    @Autowired
    private SequenceIdService sequenceIdService;

    public List<Mention> getTweets(int limit, LocalDateTime startDate, LocalDateTime endDate) {
        return mongoMentionRepository.readTweetsFromDB(limit, startDate, endDate);
    }


    public List<Mention> getMentionsByQueryId(long queryId) {
        if (mongoQueryRepository.getQueriesByID(queryId) != null) {
            return mongoMentionRepository.getMentionsByQueryID(queryId);
        } else {
            throw new QueryDoesNotExistException();
        }
    }
}

