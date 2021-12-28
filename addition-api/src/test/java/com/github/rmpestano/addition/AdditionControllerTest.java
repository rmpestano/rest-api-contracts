package com.github.rmpestano.addition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AdditionController.class)
@AutoConfigureWebTestClient(timeout = "20000")
class AdditionControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void shouldSum() {
		webTestClient.post()
				.uri("/api/addition/1/2")
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.value").isEqualTo("3");
	}

}