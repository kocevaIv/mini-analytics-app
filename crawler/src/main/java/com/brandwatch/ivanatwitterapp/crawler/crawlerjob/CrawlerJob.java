package com.brandwatch.ivanatwitterapp.crawler.crawlerjob;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.TwitterException;

import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.common.models.Query;
import com.brandwatch.ivanatwitterapp.crawler.services.MentionService;
import com.brandwatch.ivanatwitterapp.crawler.services.TwitterQueryService;

@Component
public class CrawlerJob {

    @Autowired
    private MentionService mentionService;

    @Autowired
    private TwitterQueryService twitterQueryService;

    //gets tweets in JSON format and saves them to a kafka topic
    @Scheduled(fixedDelay = 5000)
    public void getTweets() throws TwitterException {

        //loop through the saved queries
        List<Query> twitterQueries = twitterQueryService.readQueries();
        for (Query query : twitterQueries) {
          List<Resource> resources = mentionService.findResourcesForHashTag(query.getQueryDefinition());
          mentionService.saveMentions(resources, query.getId());
        }
    }
}
