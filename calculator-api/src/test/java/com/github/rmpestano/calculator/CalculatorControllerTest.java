package com.github.rmpestano.calculator;

import com.github.rmpestano.calculator.model.Result;
import com.github.rmpestano.calculator.service.AdditionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CalculatorController.class)
@AutoConfigureWebTestClient(timeout = "20000")
class CalculatorControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	@MockBean
	AdditionService additionService;

	@Test
	void shouldSum() {
		given(additionService.sum(anyInt(), anyInt())).willReturn(Mono.just(new Result(BigDecimal.valueOf(42))));
		webTestClient.post()
				.uri("/api/calculator/sum/1/2")
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.result").isEqualTo("42");
	}


}
