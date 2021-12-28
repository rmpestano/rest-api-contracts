package com.github.rmpestano.addition;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "api/addition", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AdditionController {

    @PostMapping
    public Mono<ResponseEntity<Result>> sum(@RequestBody Operator operator) {
        return Mono.just(new Result(BigDecimal.valueOf(operator.op1)
                        .add(BigDecimal.valueOf(operator.op2))))
                .map(ResponseEntity::ok);
    }

    public record Result(BigDecimal result) {}

    public record Operator(Integer op1, Integer op2) {}

}