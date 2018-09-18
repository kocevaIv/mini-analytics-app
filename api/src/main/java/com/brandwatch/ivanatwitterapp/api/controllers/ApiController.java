package com.brandwatch.ivanatwitterapp.api.controllers;

import com.brandwatch.ivanatwitterapp.api.services.QueryService;
import com.brandwatch.ivanatwitterapp.api.services.MentionService;
import com.brandwatch.ivanatwitterapp.api.utils.DateParser;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private MentionService mentionService;

    @Autowired
    private QueryService queryService;

    @GetMapping("api/getDBTweets")
    public List<Mention> getTweetsFromDB(@RequestParam int limit, @RequestParam String startDate, @RequestParam String endDate) {
        LocalDateTime startDateISO = DateParser.parseDateTime(startDate);
        LocalDateTime endDateISO = DateParser.parseDateTime(endDate);
        return mentionService.getTweets(limit, startDateISO, endDateISO);
    }

    @RequestMapping(value = "api/createQuery", method = RequestMethod.POST)
    public void saveQuery(@RequestParam String hashTag) {
        queryService.createQuery(hashTag);
    }

    @RequestMapping(value = "api/getMentions/{id}", method = RequestMethod.GET)
    public List<Mention> getMentionsByQueryId(@PathVariable long id) {
        return mentionService.getMentionsByQueryId(id);

    }

    @RequestMapping(value = "api/deleteQuery/{id}", method = RequestMethod.DELETE)
    public boolean deleteQuery(@PathVariable long id) {
        return queryService.deleteQueryById(id);
    }

    @RequestMapping(value = "api/updateQuery/{id}", method = RequestMethod.PATCH)
    public boolean updateQuery(@PathVariable long id, @RequestParam String hashTag) {
        return queryService.updateQueryForHashTag(id, hashTag);
    }
}
