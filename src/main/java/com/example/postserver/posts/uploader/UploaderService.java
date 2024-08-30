package com.example.postserver.posts.uploader;

import com.example.postserver.posts.PostController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final RestClient restClient;
    private final Logger logger = LoggerFactory.getLogger(UploaderService.class);

    public UploaderService(CircuitBreakerFactory circuitBreakerFactory, RestClient restClient) {
        circuitBreaker = circuitBreakerFactory.create("user");
        this.restClient = restClient;
    }

    public UploaderInfo getUserInfo(int userId) {
        logger.info("Sending info : " + userId);
        return
                restClient.get()
                        .uri(userServerUrl + "/api/users/" + userId)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, (request, response) -> {
                            throw new RuntimeException("invalid server response " + response.getStatusText());
                        })
                        .body(UploaderInfo.class);
    }

    public List<UploaderInfo> getAllUser() {
        return restClient.get().uri(userServerUrl+"/api/users")
                .retrieve().body(new ParameterizedTypeReference<List<UploaderInfo>>() {});
    }
}
