package com.order.controller;

import com.order.dto.OrderRequest;
import com.order.entity.Order;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order create(@RequestBody OrderRequest request) {
        Order order = Order.builder()
                .amount(request.getAmount())
                .build();

        return service.create(order);
    }

    @GetMapping("/health")
    public String health() {
        return "Order service is running";
    }
}