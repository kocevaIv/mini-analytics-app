package com.twitter.app.repositories;

import com.twitter.app.classes.TweetDocument;

public interface TwitterRepositoryCustom {

    void saveTweetToDB(TweetDocument tweetDocument);

}
