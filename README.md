# Mini analytics app [![Build Status](https://travis-ci.com/kocevaIv/mini-analytics-app.svg?branch=master)](https://travis-ci.com/kocevaIv/mini-analytics-app)
A spring boot app that crawls data from twitter in two ways:
* Crawling tweets (by hashtags) for a query which is created by the user
* Sampling tweets in real time using twitter streams API

Afterwards, the crawled tweets are being sent to a kafka topic and stored in solr database.
The user defined queries are stored independently in postgres.

## Installation

## Configuration

## Using the API
