package com.brandwatch.ivanatwitterapp.api.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandwatch.ivanatwitterapp.api.exceptions.InvalidDateRangeException;
import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;
import com.brandwatch.ivanatwitterapp.api.utils.DateUtils;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;
import com.brandwatch.ivanatwitterapp.mentionstorage.repository.MentionRepository;

@Service
public class MentionService {

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private QueryRepository queryRepository;



    public List<Mention> getMentions(Instant startDate, Instant endDate) {
        if(DateUtils.validateRequestedDateRange(startDate,endDate)) {
            return mentionRepository.readMentions(startDate, endDate);
        }
        else{
            throw new InvalidDateRangeException();
        }
    }

    public List<Mention> getMentionsByQueryId(long queryId) {
        if (queryRepository.findQueryById(queryId) != null) {
            return mentionRepository.findMentionsByQueryId(queryId);
        } else {
            throw new QueryDoesNotExistException();
        }
    }
}

