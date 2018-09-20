package com.brandwatch.ivanatwitterapp.crawler.crawlerjob;


import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.crawler.services.TwitterQueryService;
import com.brandwatch.ivanatwitterapp.crawler.services.MentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlerJob {

    @Autowired
    private MentionService mentionService;

    @Autowired
    private TwitterQueryService twitterQueryService;

    //gets tweets in JSON format and saves them to a database
    @Scheduled(fixedDelay = 5000)
    public void getTweets() {
        //loop through the saved queries
        List<TwitterQuery> twitterQueries = twitterQueryService.readQueries();
        for (TwitterQuery query : twitterQueries) {
            List<Tweet> tweets = mentionService.getMentionsForHashtag(query.getHashtag());
            mentionService.saveMentions(tweets, query.getId());
        }
    }
}
