package com.payment.controller;

import com.payment.dto.PaymentRequest;
import com.payment.entity.Payment;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public Payment create(@RequestBody PaymentRequest request) {
        return service.process(request.getOrderId(), request.getAmount());
    }

    @GetMapping("/health")
    public String health() {
        return "Payment service is running";
    }
}