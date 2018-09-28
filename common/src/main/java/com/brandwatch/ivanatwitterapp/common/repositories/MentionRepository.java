package com.brandwatch.ivanatwitterapp.common.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.brandwatch.ivanatwitterapp.common.models.Mention;

public interface MentionRepository {

    void saveMention(Mention mention);

    List<Mention> readMentions(int limit, LocalDateTime startDate, LocalDateTime endDate);

    List<Mention> findMentionsByQueryId(long queryId);
}

