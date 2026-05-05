package com.payment.kafka.producer;

import com.payment.dto.PaymentResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;

    public void sendSuccess(PaymentResultEvent event) {
        kafkaTemplate.send("payment-success", event);
    }
}