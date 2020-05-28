package com.brandwatch.ivanatwitterapp.crawler.crawlerjob;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.TwitterException;

import com.brandwatch.ivanatwitterapp.common.models.Query;
import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.crawler.services.CrawlerService;

@Component
public class CrawlerJob {

    @Autowired
    private CrawlerService crawlerService;


    //gets tweets in JSON format and saves them to a kafka topic
    @Scheduled(fixedDelay = 5000)
    public void getTweets() throws TwitterException {
        //loop through the saved queries
        List<Query> twitterQueries = crawlerService.readQueries();
        for (Query query : twitterQueries) {
          List<Resource> resources = crawlerService.findResourcesForSearchQuery(query.getQueryDefinition());
          crawlerService.saveMentions(resources, query.getId());
        }
    }
}
