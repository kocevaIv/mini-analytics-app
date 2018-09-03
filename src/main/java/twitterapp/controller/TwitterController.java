package twitterapp.controller;


import twitterapp.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(TwitterController.TWITTER_BASE_URI)
public class TwitterController {

    public static final String TWITTER_BASE_URI = "svc/v1/tweets";

    public static final String HASH_TAG = "burger";

    @Autowired
    private TwitterService twitterService;

    //gets tweets in JSON format and saves them to a database
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Scheduled(fixedDelay = 5000)
    public void getTweets() {

        //searches twitter with the specified hashtag and gets max 20 pages
        List<Tweet> tweets = twitterService.getTweetsForHashtag(HASH_TAG);
        twitterService.saveTweetsToDB(tweets);

    }


}
