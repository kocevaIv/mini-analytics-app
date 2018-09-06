package twitterapp.crawlerjob;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitterapp.services.TwitterService;

import java.util.List;

@Component
public class CrawlerJob {


    public static final String HASH_TAG = "burger";

    @Autowired
    private TwitterService twitterService;

    //gets tweets in JSON format and saves them to a database
    @Scheduled(fixedDelay = 5000)
    public void getTweets() {

        //searches twitter with the specified hashtag and gets max 20 pages
        List<Tweet> tweets = twitterService.getTweetsForHashtag(HASH_TAG);
        twitterService.saveTweetsToDB(tweets);

    }


}
