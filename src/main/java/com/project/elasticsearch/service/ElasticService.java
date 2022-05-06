package com.project.elasticsearch.service;


import com.project.elasticsearch.dto.ResponseDto;

public interface ElasticService {

    ResponseDto fullExportToIndex();

    boolean putMapping();

}
