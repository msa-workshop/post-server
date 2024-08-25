package com.example.postserver.posts.uploader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UploaderService {
    @Value("${sns.user-server}")
    private String userServerUrl;
    private final CircuitBreaker circuitBreaker;
    private RestClient restClient = RestClient.create();

    public UploaderService(CircuitBreakerFactory circuitBreakerFactory) {
        circuitBreaker = circuitBreakerFactory.create("user");
    }

    public UploaderInfo getUserInfo(int userId) {
        return circuitBreaker.run(() ->
                restClient.get()
                        .uri(userServerUrl + "/api/users/" + userId)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, (request, response) -> {
                            throw new RuntimeException("invalid server response " + response.getStatusText());
                        })
                        .body(UploaderInfo.class), throwable -> new UploaderInfo(0, "fallback", "fallback-email"));

    }

    public List<UploaderInfo> getAllUser() {
        return restClient.get().uri(userServerUrl+"/api/users")
                .retrieve().body(new ParameterizedTypeReference<List<UploaderInfo>>() {});
    }
}
