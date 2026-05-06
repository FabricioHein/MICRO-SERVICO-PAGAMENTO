package com.order.dto;

import lombok.Data;

@Data
public class PaymentResultEvent {
    private Long orderId;
    private String status;
    private String transactionId;
}