package com.project.elasticsearch.service.impl;

import static com.project.elasticsearch.utils.Constants.GENERIC_INDEX;
import static com.project.elasticsearch.utils.Constants.GENERIC_MAPPING_JSON;

import com.project.elasticsearch.client.ElasticSearchClient;
import com.project.elasticsearch.dto.ResponseDto;
import com.project.elasticsearch.repository.UserRepository;
import com.project.elasticsearch.service.AsyncIndexService;
import com.project.elasticsearch.service.ElasticService;
import com.project.elasticsearch.utils.Constants;
import com.project.elasticsearch.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

@Service
public class ElasticServiceImpl implements ElasticService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticServiceImpl.class);

    @Autowired
    ElasticSearchClient esClient;

    @Autowired
    Environment env;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AsyncIndexService asyncIndexService;

    @Value("${index.prefix}")
    private String indexPrefix;

    @Value("${index.task.executor.threads.number}")
    private Integer indexTaskExecutorThreadsNumber;

    @PostConstruct
    public void setup() {
        ClassLoader cl = this.getClass().getClassLoader();
        try(InputStream is = cl.getResourceAsStream(GENERIC_MAPPING_JSON)){
            if (!esClient.indexExists(indexPrefix + GENERIC_INDEX)) {
                esClient.createIndex(indexPrefix + GENERIC_INDEX, is);
            }
        } catch (IOException ex) {
            logger.error(Constants.ERROR_READING_FILE, ex);
        }
    }

    @Override
    public boolean putMapping(){
        ClassLoader cl = this.getClass().getClassLoader();
        try(InputStream is = cl.getResourceAsStream(GENERIC_MAPPING_JSON)){
            return esClient.putMapping(indexPrefix + GENERIC_INDEX, is).acknowledged();
        } catch (IOException ex) {
            logger.error(Constants.ERROR_READING_FILE, ex);
        }
        return false;
    }

    @Override
    public ResponseDto fullExportToIndex() {
        try {
            long start = System.nanoTime();
            logger.info(Constants.STARTING_FULL_EXPORT_TO_INDEX, indexPrefix + GENERIC_INDEX);
            //Parallel data send to elastic
            sendFullDataToElastic(indexPrefix + GENERIC_INDEX);
            long finish = System.nanoTime();
            logger.info(Constants.FINISHED_FULL_EXPORT_TO_INDEX, indexPrefix + GENERIC_INDEX, (finish - start) / 1000000000);
            return ResponseUtils.createResponse(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            logger.error(Constants.ERROR_DURING_FULL_INDEX, indexPrefix + GENERIC_INDEX, e);
            return ResponseUtils.createResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                e.getMessage());
        }
    }

    private Boolean sendFullDataToElastic(String indexName){
        return esClient.initClientAndExecute( client -> {
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            long numberOfRows = userRepository.count();
            if (numberOfRows <= 0) {
                return true;
            }
            for (int i = 0; i < indexTaskExecutorThreadsNumber; i++) {
                futures.add(asyncIndexService.fullIndexFuture(numberOfRows, i, indexTaskExecutorThreadsNumber, client, indexName));
            }
            long startTime = System.nanoTime();
            logger.info(Constants.STARTED_MERGING_FUTURES);
            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            CompletableFuture<List<Void>> resultFutureList = combinedFuture.thenApply(
                aVoid -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
            resultFutureList.get();
            long endTime = System.nanoTime();
            logger.info(Constants.FINISHED_MERGING_FUTURES, (endTime - startTime) / 1000000000);
            return true;
        });
    }

}
