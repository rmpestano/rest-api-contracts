package com.github.rmpestano.calculator;

import com.github.rmpestano.calculator.model.Result;
import com.github.rmpestano.calculator.service.AdditionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/calculator")
public class CalculatorController {

    private final AdditionService sumService;

    @PostMapping("sum/{op1}/{op2}")
    Mono<ResponseEntity<Result>> sum(@NonNull @PathVariable("op1") Integer op1, @NonNull @PathVariable("op2") Integer op2) {
        return sumService.sum(op1, op2)
                .map(ResponseEntity::ok);
    }

}
