package com.github.rmpestano.addition;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "api/addition", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdditionController {

    @PostMapping("{op1}/{op2}")
    public Mono<ResponseEntity<Result>> sum(@PathVariable("op1") Integer op1, @PathVariable("op2") Integer op2) {
        return Mono.just(new Result(BigDecimal.valueOf(op1)
                        .add(BigDecimal.valueOf(op2))))
                .map(ResponseEntity::ok);
    }

    public record Result(BigDecimal value) {}

}