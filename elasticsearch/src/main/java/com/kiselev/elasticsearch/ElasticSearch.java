package com.kiselev.elasticsearch;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ElasticSearch {

    private RestHighLevelClient restHighLevelClient;

    private ElasticSearch(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public static ElasticSearch auth(String elasticSearchHostName,
                                     Integer elasticSearchHostPort,
                                     String elasticSearchProtocol) {
        return new ElasticSearch(
                new RestHighLevelClient(
                        RestClient.builder(
                                new HttpHost(
                                        elasticSearchHostName,
                                        elasticSearchHostPort,
                                        elasticSearchProtocol
                                )
                        )
                )
        );
    }

    public IndexResponse index(IndexRequest indexRequest, Header... headers) throws IOException {
        return restHighLevelClient.index(indexRequest, headers);
    }

    public BulkResponse bulk(BulkRequest bulkRequest, Header... headers) throws IOException {
        return restHighLevelClient.bulk(bulkRequest, headers);
    }
}
