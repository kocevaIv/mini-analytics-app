package com.brandwatch.ivanatwitterapp.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandwatch.ivanatwitterapp.api.exceptions.QueryDoesNotExistException;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.repositories.MentionRepository;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;

@Service
public class MentionService {

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private QueryRepository queryRepository;

    public List<Mention> getMentions(int limit, LocalDateTime startDate, LocalDateTime endDate) {
        return mentionRepository.readMentions(limit, startDate, endDate);
    }

    public List<Mention> getMentionsByQueryId(long queryId) {
        if (queryRepository.findQueryById(queryId) != null) {
            return mentionRepository.findMentionsByQueryId(queryId);
        } else {
            throw new QueryDoesNotExistException();
        }
    }
}

