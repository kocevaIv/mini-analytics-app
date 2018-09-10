package common_services.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import common_services.models.TweetDocument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class MongoTwitterRepository implements TwitterRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveTweetToDB(TweetDocument tweetDocument) {
        mongoTemplate.save(tweetDocument);
    }

    @Override
    public List<TweetDocument> readTweetsFromDB(int limit, LocalDate startDate, LocalDate endDate) {

        Query query=new Query();
        query.limit(limit);
        query.addCriteria(Criteria.where("createdAt").lt(endDate).gt(startDate));
        return mongoTemplate.find(query,TweetDocument.class);

    }
}
