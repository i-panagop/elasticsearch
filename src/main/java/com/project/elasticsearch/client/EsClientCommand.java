package com.project.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

public interface EsClientCommand<T> {
    T execute(ElasticsearchClient client) throws Exception;
}
