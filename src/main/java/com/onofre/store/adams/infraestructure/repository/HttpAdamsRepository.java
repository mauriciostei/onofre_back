package com.onofre.store.adams.infraestructure.repository;

import com.onofre.store.adams.infraestructure.properties.AdamsProperties;
import com.onofre.store.adams.mapper.AdamsMapper;
import com.onofre.store.store.infraestructure.dao.StoreDAO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Map;

@Repository
public class HttpAdamsRepository implements AdamsRepository{
    private final RestClient client;
    private final AdamsProperties properties;
    private final AdamsMapper mapper;

    public HttpAdamsRepository(AdamsProperties properties, AdamsMapper mapper) {
        this.properties = properties;
        this.mapper = mapper;

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(Duration.ofSeconds(10));

        this.client = RestClient.builder()
                .baseUrl(properties.getUrl())
                .defaultHeader("Accept", "application/json")
                .requestFactory(requestFactory)
                .build();
    }

    @Override
    public Map<String, Object> createDeb(StoreDAO store) throws Exception {
        String baseUrl = properties.getUrl() + "/debts";

//        return mapper.toCreateDebs(store);
         Map<String, Object> body = mapper.toCreateDebs(store);

         return client.post()
                 .uri(baseUrl)
                 .body(body)
                 .headers(headers -> {
                     headers.set("apikey", properties.getApikey());
                 })
                 .retrieve()
                 .body(new ParameterizedTypeReference<Map<String, Object>>() {})
                 ;
    }

    @Override
    public Map<String, Object> retrieveDeb(String id) throws Exception {
        String baseUrl = properties.getUrl() + "/debts/" + id;

        return client.get()
                .uri(baseUrl)
                .headers(headers -> {
                    headers.set("apikey", properties.getApikey());
                })
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {})
                ;
    }
}
