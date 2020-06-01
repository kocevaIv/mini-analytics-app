package com.brandwatch.ivanatwitterapp.mentionstorage.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.brandwatch.ivanatwitterapp.common.models.Mention;

@Repository
public interface MentionRepository extends SolrCrudRepository<Mention, String> {

    @Query("*:*")
    List<Mention> readAllMentions();

    @Query(value = "*:*", filters = "createdAt:[?0 TO ?1]")
    List<Mention> readMentions(Instant fromDate, Instant toDate);

    List<Mention> findMentionsByQueryId(long queryId);
}
