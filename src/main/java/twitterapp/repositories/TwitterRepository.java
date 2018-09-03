package twitterapp.repositories;

import twitterapp.models.TweetDocument;

public interface TwitterRepository {

    void saveTweetToDB(TweetDocument tweetDocument);
}
