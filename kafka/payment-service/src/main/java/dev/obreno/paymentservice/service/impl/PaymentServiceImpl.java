package dev.obreno.paymentservice.service.impl;

import dev.obreno.paymentservice.model.Payment;
import dev.obreno.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    @SneakyThrows
    @Override
    public void sendPayment(Payment payment) {
       log.info("Payment Received: {}", payment);
       Thread.sleep(1000);

       log.info("Sending payment...");
       kafkaTemplate.send("payment-topic", payment);
    }
}
