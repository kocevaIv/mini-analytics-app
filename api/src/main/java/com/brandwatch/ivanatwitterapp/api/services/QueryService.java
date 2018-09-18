package com.brandwatch.ivanatwitterapp.api.services;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyHashTagException;
import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    @Autowired
    private MongoQueryRepository mongoQueryRepository;

    @Autowired
    private SequenceIdService sequenceIdService;

    public void createQuery(String hashtag) {
        if (hashtag != "") {
            TwitterQuery twitterQuery = new TwitterQuery(sequenceIdService.getNextSequence("customSequences"), hashtag);
            mongoQueryRepository.saveQueriesToDB(twitterQuery);
        } else {
            throw new EmptyHashTagException();
        }


    }

    public TwitterQuery getQueryByID(long queryId) {
        return mongoQueryRepository.getQueriesByID(queryId);
    }

    public boolean deleteQueryById(long queryId) {
        return mongoQueryRepository.deleteQuery(queryId);
    }

    public boolean updateQueryForHashTag(long queryId, String hashTag) {
        if (hashTag != "") {
            return mongoQueryRepository.updateQuery(queryId, hashTag);

        } else {
            throw new EmptyHashTagException();
        }
    }
}
