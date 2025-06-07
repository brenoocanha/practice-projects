package dev.obreno.strproducer.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
public class StringProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StringProducerService(@Qualifier("kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("str-topic", message);
        future.whenComplete((r, e) -> {
            if (e != null) {
                log.error("Error sending message: {}", e.getMessage());
                return;
            }

            log.info("Message sent successfully: {}", r.getProducerRecord().value());
            log.info("Partition {}, Offset {}", r.getRecordMetadata().partition(), r.getRecordMetadata().offset());
        });
    }
}
