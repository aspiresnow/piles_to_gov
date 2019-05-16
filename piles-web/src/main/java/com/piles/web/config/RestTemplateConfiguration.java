package com.piles.web.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfiguration {

    /**
     * 连接池管理器 HttpClient是线程安全的 且可同时执行多个线程的请求 管理持久的http连接 节省创建连接时间
     * 维护的连接数在每个路由基础和总数上都有限制
     */
    @Bean
    public HttpClientConnectionManager pollingConnectionManager() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        //最大连接数
        manager.setMaxTotal(200);
        //每个路由基础的连接
        manager.setDefaultMaxPerRoute(5);
        return manager;
    }

    @Bean
    public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager httpClientConnectionManager) {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setConnectionManager(httpClientConnectionManager);
        return builder;
    }

    @Bean
    public HttpClient httpClient(HttpClientBuilder builder) {
        return builder.build();
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        //连接超时时长
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(5000);
        return httpRequestFactory;
    }

    /**
     * 带有连接池的 RestTemplate
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setMessageConverters(buildNessageConverters());
        return restTemplate;
    }

    private List<HttpMessageConverter<?>> buildNessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        return messageConverters;
    }
}
