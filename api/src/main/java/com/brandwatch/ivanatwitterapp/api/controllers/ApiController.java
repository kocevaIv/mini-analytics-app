package com.brandwatch.ivanatwitterapp.api.controllers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.ivanatwitterapp.api.services.MentionService;
import com.brandwatch.ivanatwitterapp.api.services.QueryService;
import com.brandwatch.ivanatwitterapp.api.utils.DateUtils;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.Query;

@RestController
public class ApiController {

    @Autowired
    private MentionService mentionService;

    @Autowired
    private QueryService queryService;

    @GetMapping("/mentions")
    public List<Mention> getMentions(@RequestParam("startDate")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                     @RequestParam("endDate")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {


        Instant parsedStartDate = DateUtils.parseDate(startDate);
        Instant parsedEndDate = DateUtils.parseDate(endDate);
        return mentionService.getMentions(parsedStartDate, parsedEndDate);
    }

    @PostMapping("/queries")
    public void saveQuery(@RequestParam("definition") String definition) {
        queryService.createQuery(definition);
    }

    @GetMapping("/queries")
    public List<Query> readAllQueries() {
        return queryService.getAllQueries();
    }

    @GetMapping(value = "/mentions/{id}")
    public List<Mention> getMentionsByQueryId(@PathVariable long id) {
        return mentionService.getMentionsByQueryId(id);
    }

    @DeleteMapping("/queries/{id}")
    public void deleteQuery(@PathVariable long id) {
        queryService.deleteQueryById(id);
    }

    @PatchMapping("/queries/{id}")
    public void updateQuery(@PathVariable long id, @RequestParam String definition) {
        queryService.updateQueryDefinition(id, definition);
    }
}
