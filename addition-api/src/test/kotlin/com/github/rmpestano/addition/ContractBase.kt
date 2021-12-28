package com.github.rmpestano.addition

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.ComponentScan


@SpringBootTest(classes = [AdditionController::class, ContractBase.Config::class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class ContractBase {

    @LocalServerPort
    var port = 0

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:$port"
    }

    @TestConfiguration
    @ComponentScan
    @EnableAutoConfiguration
    open class Config {

    }
}