package com.payment.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.dto.OrderCreatedEvent;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService service;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void consume(String message) {
        try {
            OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);
            service.process(event.getOrderId(), event.getAmount());
            System.out.println("Pagamento processado: " + event.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}