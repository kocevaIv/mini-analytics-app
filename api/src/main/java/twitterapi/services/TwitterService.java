package twitterapi.services;


import common_services.models.TweetDocument;
import common_services.repositories.MongoTwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    MongoTwitterRepository mongoTwitterRepository;

    public List<TweetDocument> getTweets(int limit, LocalDate startDate, LocalDate endDate){
        return mongoTwitterRepository.readTweetsFromDB(limit,startDate,endDate);
    }
}
