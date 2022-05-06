package com.project.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface AsyncIndexService {

    CompletableFuture<Void> fullIndexFuture(long numberOfRows, int loop, Integer indexTaskExecutorThreadsNumber, ElasticsearchClient esClient,
        String indexName) throws IOException;
}
