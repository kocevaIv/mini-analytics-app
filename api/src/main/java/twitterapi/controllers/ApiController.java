package twitterapi.controllers;

import common_services.models.TweetDocument;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitterapi.services.TwitterService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    TwitterService twitterService;

    @GetMapping("twitterapi/getDBTweets")
    public List<TweetDocument> getTweetsFromDB(@RequestParam String limit, @RequestParam String startDate, @RequestParam String endDate) {
        int limit_parameter = Integer.parseInt(limit);
        LocalDate startDateISO = LocalDate.parse(startDate);
        LocalDate endDateISO = LocalDate.parse(endDate);
        return twitterService.getTweets(limit_parameter, startDateISO, endDateISO);

    }

}
