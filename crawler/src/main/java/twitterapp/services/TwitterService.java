package twitterapp.services;

import common_services.models.TweetDocument;
import common_services.repositories.MongoTwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private Twitter twitter;

    @Autowired
    MongoTwitterRepository twitterRepository;

    public List<Tweet> getTweetsForHashtag(String hashTag) {

        return twitter.searchOperations().search(hashTag, 20).getTweets();

    }

    public void saveTweetsToDB(List<Tweet> tweets) {
        //for each fetched tweet a new TweetDocument is created and stored in the database
        for (Tweet tweet : tweets) {
            TweetDocument tweetDocument = new TweetDocument(tweet.getId(), tweet.getText(), tweet.getFromUser(), tweet.getSource(), tweet.getCreatedAt());
            twitterRepository.saveTweetToDB(tweetDocument);
        }
    }


}
