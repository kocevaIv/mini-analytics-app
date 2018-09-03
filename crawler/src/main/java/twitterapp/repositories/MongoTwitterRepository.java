package twitterapp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import twitterapp.models.TweetDocument;

@Repository
public class MongoTwitterRepository implements TwitterRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveTweetToDB(TweetDocument tweetDocument) {
        mongoTemplate.save(tweetDocument);
    }
}
