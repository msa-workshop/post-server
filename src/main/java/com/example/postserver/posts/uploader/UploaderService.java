package com.example.postserver.posts.uploader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UploaderService {
    @Value("${sns.user-server}")
    private String userServerUrl;
    private RestClient restClient = RestClient.create();

    public UploaderInfo getUserInfo(int userId) {
        return restClient.get()
                .uri(userServerUrl + "/api/users/" + userId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new RuntimeException("invalid server response " + response.getStatusText());
                })
                .body(UploaderInfo.class);
    }
}
