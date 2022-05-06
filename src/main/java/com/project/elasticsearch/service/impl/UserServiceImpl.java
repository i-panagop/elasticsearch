package com.project.elasticsearch.service.impl;


import static com.project.elasticsearch.utils.Constants.EMAIL;
import static com.project.elasticsearch.utils.Constants.FIRST_NAME;
import static com.project.elasticsearch.utils.Constants.LAST_NAME;

import com.project.elasticsearch.client.ElasticSearchClient;
import com.project.elasticsearch.dto.elastic.UserElasticDto;
import com.project.elasticsearch.dto.user.LightUserDto;
import com.project.elasticsearch.service.ElasticService;
import com.project.elasticsearch.service.UserService;
import com.project.elasticsearch.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    ElasticService elasticService;

    @Autowired
    ElasticSearchClient esClient;

    @Autowired
    Environment env;

    @Value("${index.prefix}")
    private String indexPrefix;

    @Override
    public String getIndexName() {
        return this.indexPrefix + Constants.GENERIC_INDEX;
    }

    @Override
    public Type getTargetListType() {
        return new TypeToken<List<LightUserDto>>() {
        }.getType();
    }

    @Override
    public <T> T getTargetType() {
        return (T) UserElasticDto.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PropertyMap getPropertyMapElastic() {
        return null;
    }

    @Override
    public String[] getSearchablefields() {
        return new String[]{FIRST_NAME, EMAIL, LAST_NAME};
    }



    @Override
    public ElasticSearchClient getClient() {
        return this.esClient;
    }
}
