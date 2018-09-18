package com.brandwatch.ivanatwitterapp.common.repositories;

import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

@Repository
public class MongoQueryRepository implements QueryRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveQueriesToDB(TwitterQuery query) {
        mongoTemplate.save(query);
    }

    @Override
    public List<TwitterQuery> getQueriesFromDB() {
        return mongoTemplate.findAll(TwitterQuery.class);
    }

    @Override
    public TwitterQuery getQueriesByID(long QueryID) {
        return mongoTemplate.findById(QueryID, TwitterQuery.class);
    }

    @Override
    public boolean deleteQuery(long queryId) {
        Query query = new Query();
        WriteResult writeResult = mongoTemplate.remove(query.addCriteria(where("id").is(queryId)), TwitterQuery.class);
        if (writeResult.getN() != 0) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean updateQuery(long queryId, String hashTag) {
        Query query = new Query(where("id").is(queryId));
        Update update = update("hashTag", hashTag);
        WriteResult writeResult = mongoTemplate.updateFirst(query, update, TwitterQuery.class);
        if (writeResult.getN() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
