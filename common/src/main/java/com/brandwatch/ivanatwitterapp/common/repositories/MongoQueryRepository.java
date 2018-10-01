package com.brandwatch.ivanatwitterapp.common.repositories;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;

@Repository
public class MongoQueryRepository implements QueryRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public TwitterQuery saveQuery(TwitterQuery query) {
        mongoTemplate.save(query);
        return query;
    }

    @Override
    public List<TwitterQuery> readQueries() {
        return mongoTemplate.findAll(TwitterQuery.class);
    }

    @Override
    public TwitterQuery findQueryById(long queryId) {
        return mongoTemplate.findById(queryId, TwitterQuery.class);
    }

    @Override
    public boolean deleteQuery(long queryId) {
        Query query = new Query().addCriteria(where("id").is(queryId));
        DeleteResult deleteResult = mongoTemplate.remove(query, TwitterQuery.class);
        return deleteResult.getDeletedCount() != 0;
    }

    @Override
    public TwitterQuery updateQuery(long queryId, String hashtag) {
        Query query = new Query(where("id").is(queryId));
        Update update = update("hashtag", hashtag);
        mongoTemplate.updateFirst(query, update, TwitterQuery.class);
        return mongoTemplate.findById(queryId, TwitterQuery.class);
    }
}
