package com.example.postserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class PostServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostServerApplication.class, args);
    }

}
