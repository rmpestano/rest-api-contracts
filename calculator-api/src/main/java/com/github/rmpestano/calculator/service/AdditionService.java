package com.github.rmpestano.calculator.service;

import com.github.rmpestano.calculator.model.Result;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AdditionService {

    @Qualifier("additionClient")
    private final WebClient webClient;

    public Mono<Result> sum(@NonNull final Integer op1, @NonNull final Integer op2) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/{op1}/{op2}")
                        .build(op1, op2))
                .retrieve()
                .bodyToMono(Result.class);
    }
}
