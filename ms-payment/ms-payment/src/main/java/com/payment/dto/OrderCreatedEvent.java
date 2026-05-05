package com.payment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreatedEvent {
    private Long orderId;
    private BigDecimal amount;
}