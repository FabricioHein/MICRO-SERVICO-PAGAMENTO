package com.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResultEvent {
    private Long orderId;
    private String status;
    private String transactionId;
}