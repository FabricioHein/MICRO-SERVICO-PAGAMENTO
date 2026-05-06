package com.order.kafka.consumer;

import com.order.dto.PaymentResultEvent;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final OrderService service;

    @KafkaListener(topics = "payment-success", groupId = "order-group")
    public void success(PaymentResultEvent event) {
        service.updateStatus(event.getOrderId(), "SUCCESS");
        System.out.println("Pedido pago: " + event.getOrderId());
    }

    @KafkaListener(topics = "payment-failed", groupId = "order-group")
    public void failed(PaymentResultEvent event) {
        service.updateStatus(event.getOrderId(), "FAILED");
        System.out.println("Pedido falhou: " + event.getOrderId());
    }
}