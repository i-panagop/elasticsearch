package com.project.elasticsearch.service;

import com.project.elasticsearch.model.User;
import org.modelmapper.PropertyMap;

public interface UserService extends ElasticSearchService<User> {

    PropertyMap getPropertyMapElastic();

}
