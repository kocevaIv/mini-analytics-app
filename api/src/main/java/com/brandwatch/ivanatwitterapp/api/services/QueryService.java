package com.brandwatch.ivanatwitterapp.api.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyHashTagException;
import com.brandwatch.ivanatwitterapp.api.repositories.SequenceIdRepository;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;

@Service
public class QueryService {

    @Autowired
    private MongoQueryRepository mongoQueryRepository;

    @Autowired
    private SequenceIdRepository sequenceIdRepository;

    public TwitterQuery createQuery(String hashtag) {
        if (StringUtils.isNotBlank(hashtag)) {
            TwitterQuery twitterQuery = new TwitterQuery(sequenceIdRepository.getNextSequence("customSequences"), hashtag);
            mongoQueryRepository.saveQuery(twitterQuery);
            return twitterQuery;
        } else {
            throw new EmptyHashTagException();
        }
    }

    public TwitterQuery getQueryByID(long queryId) {
        return mongoQueryRepository.findQueryById(queryId);
    }

    public boolean deleteQueryById(long queryId) {
        return mongoQueryRepository.deleteQuery(queryId);
    }

    public boolean updateQueryForHashTag(long queryId, String hashtag) {
        if (StringUtils.isNotBlank(hashtag)) {
            return mongoQueryRepository.updateQuery(queryId, hashtag);
        } else {
            throw new EmptyHashTagException();
        }
    }
}
