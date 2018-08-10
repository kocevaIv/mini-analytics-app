package com.twitter.app.repositories;

import com.twitter.app.classes.TweetDocument;
import org.springframework.data.repository.CrudRepository;

public interface TwitterRepository extends CrudRepository<TweetDocument,Long>,TwitterRepositoryCustom {

}
