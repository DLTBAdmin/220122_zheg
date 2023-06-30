package com.atguigu.gulimall.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
/**
 * 1.导入依赖
 * 2.编写配置,给容器中注入一个RestHighLevelClient
 *
 * */
@Configuration
public class GulimallElasticSearchConfig {

    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();

        //

        COMMON_OPTIONS = builder.build();
    }

    public RestHighLevelClient esRestClient(){

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.120.130", 9200, "http")));

        return client;
    }

}
