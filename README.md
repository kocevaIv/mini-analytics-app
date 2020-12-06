# Mini analytics app [![Build Status](https://travis-ci.com/kocevaIv/mini-analytics-app.svg?branch=master)](https://travis-ci.com/kocevaIv/mini-analytics-app)
A spring boot app that crawls data from twitter in two ways:
* Crawling tweets by a user defined boolean query 
* Sampling tweets in real time using twitter streams API

## Overview
The app consists of 4 services:
- api 
- crawler
- mentions-storage
- mentions-matcher
- resource-filterer

And a commons-library for re-using code between services.

The crawled tweets are being sent to a Kafka topic - `resource-filtering` and they are filtered in 
the resource-filterer module. This service filters out duplicate resources and stores them in a Solr collection called `resources`
The filtered resources are then send to another Kafka topic `resource-matching` and are consumed in the mentions-matcher service.
The mentions-matcher service matches all the resources with the stored queries in postgres and creates mentions. Finally, these mentions are
send to a Kafka topic called `mentions` and are consumed in mentions-storage service. The sole purpose of mentions-storage service is to store mentions in Solr and return stored mentions from a Solr collection `mentions`.

## Configuration

Before being able to run the application, you will need to have on your machine installed:
 - [Docker](https://docs.docker.com/get-docker/) 
 - [Maven](https://maven.apache.org/install.html)

To be able to crawl data from Twitter in the app, you will need to set up your own [Twitter developer account](https://developer.twitter.com/en/apply-for-access) and configure your API keys as specified inside the
 [twitter4j.properties file](crawler/src/main/resources/twitter4j.properties) 

## Installation


Before running the application inside docker, you will need to run:
```
mvn clean install
```

After the installation, navigate to the location where the docker-compose.yaml file is and run:
```
docker-compose up
```
To interact with the app, open the browser and navigate to `localhost:8080/` 



## Using the API
The API provides several endpoints:

### Mention endpoints:
 1. `GET /mentions`

 2. `GET /mentions/{id}`
 

### Query endpoints:
 1. `GET /queries`
 
 2.  `POST /queries`
 
 3.  `PATCH /queries/{id}`
 
 4.  `DELETE /query/{id}`
 

