package com.project.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.AcknowledgedResponse;
import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ElasticSearchClient {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchClient.class);

    @Value("${elastic.client.hostname}")
    private String hostName;

    @Value("${elastic.client.port}")
    private int port;

    @Value("${elastic.client.scheme}")
    private String scheme;

    @Value("${elastic.client.username}")
    private String username;

    @Value("${elastic.client.password}")
    private String password;

    public RestClientTransport getTransport() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        RestClient restClient = RestClient.builder(new HttpHost(this.hostName, this.port, this.scheme))
            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
            .setRequestConfigCallback(
                requestConfigBuilder -> requestConfigBuilder
                    .setConnectTimeout(5000)
                    .setSocketTimeout(180000)
            ).build();
        return new RestClientTransport(restClient, new JacksonJsonpMapper());
    }

    public <T> T initClientAndExecute(EsClientCommand<T> command){
        try(RestClientTransport transport = getTransport()){
            ElasticsearchClient client = new ElasticsearchClient(transport);
            return command.execute(client);
        }catch (Exception e){
            logger.error("error in initClientAndExecute.", e);
        }
        return null;
    }

    public Boolean indexExists(String indexName){
        return initClientAndExecute( client ->  client.indices().exists( r -> r.index(indexName)).value());
    }

    public CreateIndexResponse createIndex(String indexName, InputStream mapping) {
        return initClientAndExecute( client ->
            client.indices().create(
                cr -> cr.index(indexName)
                    .mappings( TypeMapping.of( builder -> builder.withJson(mapping)))
                    .settings(
                        IndexSettings.of( builder -> builder
                        .numberOfShards("1")
                        .numberOfReplicas("0")
                        .refreshInterval(Time.of(timeBuilder -> timeBuilder.time("1s"))))
                    )
            )
        );
    }

    public AcknowledgedResponse putMapping(String indexName, InputStream mapping){
        return initClientAndExecute( client ->  client.indices().putMapping( pr -> pr.index(indexName).withJson(mapping)));
    }
}
