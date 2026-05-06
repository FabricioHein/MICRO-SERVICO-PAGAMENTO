package com.order.service;

import com.order.dto.OrderCreatedEvent;
import com.order.entity.Order;
import com.order.entity.OrderStatus;
import com.order.kafka.producer.OrderProducer;
import com.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderProducer producer;

    public Order create(Order order) {

        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        Order saved = repository.save(order);

        producer.sendOrderCreated(
                OrderCreatedEvent.builder()
                        .orderId(saved.getId())
                        .amount(saved.getAmount())
                        .build()
        );

        return saved;
    }

    public void updateStatus(Long orderId, String status) {
        Order order = repository.findById(orderId).orElseThrow();

        if ("SUCCESS".equals(status)) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.FAILED);
        }

        repository.save(order);
    }
}