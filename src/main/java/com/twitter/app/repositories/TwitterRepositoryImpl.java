package com.twitter.app.repositories;

import com.twitter.app.classes.TweetDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class TwitterRepositoryImpl implements TwitterRepositoryCustom {

@Autowired
private MongoTemplate mongoTemplate;


    @Override
    public void saveTweetToDB(TweetDocument tweetDocument) {
        mongoTemplate.save(tweetDocument);
    }
}
