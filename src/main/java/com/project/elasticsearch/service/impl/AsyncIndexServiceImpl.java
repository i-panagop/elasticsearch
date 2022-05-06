package com.project.elasticsearch.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.elasticsearch.dto.elastic.ElasticDto;
import com.project.elasticsearch.dto.elastic.UserElasticDto;
import com.project.elasticsearch.model.User;
import com.project.elasticsearch.repository.UserRepository;
import com.project.elasticsearch.service.AsyncIndexService;
import com.project.elasticsearch.utils.Utils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AsyncIndexServiceImpl implements AsyncIndexService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncIndexServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    @Async("indexExecutor")
    public CompletableFuture<Void> fullIndexFuture(long numberOfRows, int loop, Integer indexTaskExecutorThreadsNumber, ElasticsearchClient esClient,
        String indexName) throws IOException {
        CompletableFuture<Void> future = new CompletableFuture<>();
        int pageSize = Utils.calculatePageSize(numberOfRows, loop, indexTaskExecutorThreadsNumber);
        if (pageSize == 0) {
            future.complete(null);
            return future;
        }
        Pageable pageable = PageRequest.of(loop, pageSize);
        handleUserData(indexName, loop, pageSize, pageable, esClient);
        future.complete(null);
        return future;
    }

    private void handleUserData(String indexName, int loop, int pageSize, Pageable pageable, ElasticsearchClient esClient) throws IOException {
        logger.info("Current run: {}, loop size: {} for index {}", loop, pageSize, indexName);
        logger.info("Current run: {}, starting postgres read for index {}", loop, indexName);
        long startTime = System.nanoTime();
        Page<User> users = userRepository.findAll(pageable);
        if (users.getSize() <= 0) {
            return;
        }
        long endTime = System.nanoTime();
        logger.info("Current run: {}, time to read user from repository: {}", loop, (endTime - startTime) / 1000000000);
        List<ElasticDto> elasticDtos = users.get().map(UserElasticDto::new).collect(Collectors.toList());
        sendData(indexName, elasticDtos, esClient, loop);
    }

    private void sendData(String indexName, List<ElasticDto> elasticDtos, ElasticsearchClient esClient, int loop) throws IOException {
        long startTime = System.nanoTime();
        List<List<ElasticDto>> elasticDtosList = ListUtils.partition(elasticDtos, 50);
        for(List<ElasticDto> elasticDtosSubList : elasticDtosList){
            BulkRequest.Builder br = new BulkRequest.Builder();
            elasticDtosSubList.forEach( elasticDto ->
                br.operations(op -> op.index(idx -> idx.index(indexName).id(elasticDto.getId()).document(elasticDto)))
            );
            esClient.bulk(br.build());
        }
        long endTime = System.nanoTime();
        logger.info("Current run: {}, time to add requests to bulkProcessor: {} for index {}", loop, (endTime - startTime) / 1000000000, indexName);
    }
}
