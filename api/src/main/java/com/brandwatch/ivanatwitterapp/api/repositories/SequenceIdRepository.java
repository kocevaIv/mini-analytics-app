package com.brandwatch.ivanatwitterapp.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.brandwatch.ivanatwitterapp.api.models.CustomSequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class SequenceIdRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public int getNextSequence(String sequenceName) {
        CustomSequence counter = mongoOperations.findAndModify(query(Criteria.where("_id").is(sequenceName)),
                new Update().inc("value", 1),
                options().returnNew(true).upsert(true),
                CustomSequence.class);
        return counter.getValue();
    }
}
