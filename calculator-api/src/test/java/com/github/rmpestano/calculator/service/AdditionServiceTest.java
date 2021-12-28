package com.github.rmpestano.calculator.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.rmpestano.calculator.model.Result;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.spec.internal.MediaTypes;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(ids = "com.github.rmpestano:addition-api")
class AdditionServiceTest {

    @Autowired
    private StubFinder stubFinder;

    private AdditionService additionService;

    @BeforeEach
    public void setupClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(
                        "http://localhost:" + stubFinder.findAllRunningStubs()
                                .getPort("addition-api") + "/api/addition"
                ).build();
        additionService = new AdditionService(webClient);
    }

    @Test
    void shouldSum() {
        Result result = additionService.sum(1, 2).block();
        assertThat(result).isNotNull()
                .extracting(Result::value)
                .isEqualTo(BigDecimal.valueOf(3));
    }
}
