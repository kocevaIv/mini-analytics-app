package com.brandwatch.ivanatwitterapp.api.integrationtests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.MentionID;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.MentionRepository;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;

public class DatabaseManager {

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private QueryRepository queryRepository;

    public DatabaseManager() {
    }

    private void populateTestDatabase() throws ParseException {
        TwitterQuery twitterQuery = new TwitterQuery(1, "brandwatch");
        TwitterQuery anotherTwitterQuery = new TwitterQuery(2, "spotify");
        queryRepository.saveQuery(twitterQuery);
        queryRepository.saveQuery(anotherTwitterQuery);
        DateFormat isoFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Mention mentionNo1 = new Mention(new MentionID(37294979,
                twitterQuery.getId()),
                "Some random tweeted text",
                "Dummy user",
                "web client",
                isoFormat.parse("2018-09-23"));
        Mention mentionNo2 = new Mention(new MentionID(37294978,
                twitterQuery.getId()),
                "Some random tweeted text",
                "Dummy user",
                "web client",
                isoFormat.parse("2018-09-22"));
        mentionRepository.saveMention(mentionNo1);
        mentionRepository.saveMention(mentionNo2);
    }
}
