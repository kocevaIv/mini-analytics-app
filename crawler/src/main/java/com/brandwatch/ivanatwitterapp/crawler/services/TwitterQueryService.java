package com.brandwatch.ivanatwitterapp.crawler.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandwatch.ivanatwitterapp.common.models.Query;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;

@Service
public class TwitterQueryService {

//    @Autowired
//    private MentionRepository mentionRepository;
//
//    public List<Query> readQueries() {
//        return mentionRepository.readQueries();
//    }

    @Autowired
    QueryRepository queryRepository;



    public List<Query> readQueries(){

    return queryRepository.readQueries();
    }
}
