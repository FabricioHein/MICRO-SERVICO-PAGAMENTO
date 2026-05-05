package com.payment.service;

import com.payment.dto.PaymentResultEvent;
import com.payment.entity.Payment;
import com.payment.entity.PaymentStatus;
import com.payment.kafka.producer.PaymentProducer;
import com.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentProducer producer;

    public Payment process(Long orderId, BigDecimal amount) {

        Optional<Payment> existing = repository.findByOrderId(orderId);
        if (existing.isPresent()) {
            return existing.get();
        }

        Payment payment = Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .status(PaymentStatus.SUCCESS)
                .transactionId(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .build();

        Payment saved = repository.save(payment);

        producer.sendSuccess(
                PaymentResultEvent.builder()
                        .orderId(saved.getOrderId())
                        .status(saved.getStatus().name())
                        .transactionId(saved.getTransactionId())
                        .build()
        );

        return saved;
    }
}