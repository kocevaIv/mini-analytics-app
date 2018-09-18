package com.brandwatch.ivanatwitterapp.crawler.services;

import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterQueryService {

    @Autowired
    private Twitter twitter;

    @Autowired
    private MongoQueryRepository mongoQueryRepository;

    public List<TwitterQuery> getQueriesFromDB() {
        return mongoQueryRepository.getQueriesFromDB();
    }

}
