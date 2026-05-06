package com.payment.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.dto.OrderCreatedEvent;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final PaymentService service;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void consume(String message) {

        log.info("=================================================");
        log.info("📩 EVENTO RECEBIDO NO PAYMENT-SERVICE");
        log.info("TOPIC: order-created");
        log.info("PAYLOAD: {}", message);
        log.info("=================================================");

        try {

            OrderCreatedEvent event =
                    objectMapper.readValue(message, OrderCreatedEvent.class);

            log.info("🧾 Pedido convertido com sucesso");
            log.info("➡ Order ID: {}", event.getOrderId());
            log.info("➡ Amount: {}", event.getAmount());

            service.process(event.getOrderId(), event.getAmount());

            log.info("✅ Pagamento processado com sucesso");
            log.info("💰 Pedido {} finalizado", event.getOrderId());

        } catch (Exception e) {

            log.error("❌ ERRO AO PROCESSAR EVENTO");
            log.error("Payload recebido: {}", message);
            log.error("Erro: {}", e.getMessage(), e);

        }
    }
}