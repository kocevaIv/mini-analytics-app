package com.brandwatch.ivanatwitterapp.api.services;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyHashTagException;
import com.brandwatch.ivanatwitterapp.api.repositories.SequenceIdRepository;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class QueryService {

    @Autowired
    private MongoQueryRepository mongoQueryRepository;

    @Autowired
    private SequenceIdRepository sequenceIdRepository;

    public TwitterQuery createQuery(String hashtag) {
        TwitterQuery twitterQuery;
        if (!StringUtils.isEmpty(hashtag)) {
            twitterQuery = new TwitterQuery(sequenceIdRepository.getNextSequence("customSequences"), hashtag);
            mongoQueryRepository.saveQuery(twitterQuery);
        } else {
            throw new EmptyHashTagException();
        }
        return twitterQuery;
    }

    public TwitterQuery getQueryByID(long queryId) {
        return mongoQueryRepository.getQueryById(queryId);
    }

    public boolean deleteQueryById(long queryId) {
        return mongoQueryRepository.deleteQuery(queryId);
    }

    public boolean updateQueryForHashTag(long queryId, String hashtag) {
        if (!StringUtils.isEmpty(hashtag)) {
            return mongoQueryRepository.updateQuery(queryId, hashtag);
        } else {
            throw new EmptyHashTagException();
        }
    }
}
