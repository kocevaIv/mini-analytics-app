package common_services.repositories;

import common_services.models.TweetDocument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TwitterRepository {

    void saveTweetToDB(TweetDocument tweetDocument);
    List<TweetDocument> readTweetsFromDB(int limit, LocalDate startDate, LocalDate endDate);
}
