package com.github.rmpestano.calculator.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String SUM_ENDPOINT = "http://localhost:8181/api/addition";

    @Bean
    @Qualifier("additionClient")
    WebClient additionWebClient() {
        return WebClient.builder()
                .baseUrl(SUM_ENDPOINT)
                .build();
    }

}
