package com.brandwatch.ivanatwitterapp.common.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.brandwatch.ivanatwitterapp.common.models.Mention;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MongoMentionRepository implements MentionRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveMention(Mention mention) {
        mongoTemplate.save(mention);
    }

    @Override
    public List<Mention> readMentions(int limit, LocalDateTime startDate, LocalDateTime endDate) {

        Query query = new Query();
        query.limit(limit);
        query.addCriteria(Criteria.where("createdAt").lt(endDate).gt(startDate));
        return mongoTemplate.find(query, Mention.class);
    }

    @Override
    public List<Mention> getMentions() {
        return mongoTemplate.findAll(Mention.class);
    }

    @Override
    public List<Mention> getMentionsByQueryId(long queryId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id.queryId").is(queryId));
        return mongoTemplate.find(query, Mention.class);
    }
}
