package com.project.elasticsearch.controller;

import com.project.elasticsearch.client.ElasticSearchClient;
import com.project.elasticsearch.dto.ResponseDto;
import com.project.elasticsearch.service.ElasticService;
import com.project.elasticsearch.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private ElasticSearchClient esClient;

    @Autowired
    private ElasticService esService;

    @ResponseBody
    @GetMapping("/exists")
    public ResponseDto indexExists(@RequestParam String indexName) {
        return ResponseUtils.createResponse(HttpStatus.OK.toString(), HttpStatus.OK.toString(),
            String.valueOf(Boolean.TRUE.equals(esClient.indexExists(indexName))));
    }

    @ResponseBody
    @PostMapping("/put-mapping")
    public ResponseDto pubMapping() {
        return ResponseUtils.createResponse(HttpStatus.OK.toString(), HttpStatus.OK.toString(),
            String.valueOf(Boolean.TRUE.equals(esService.putMapping())));
    }

    @ResponseBody
    @PostMapping("/full-index")
    public ResponseDto fullIndex() {
        return esService.fullExportToIndex();
    }

}
