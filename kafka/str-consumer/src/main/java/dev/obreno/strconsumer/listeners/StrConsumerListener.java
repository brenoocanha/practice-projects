package dev.obreno.strconsumer.listeners;

import dev.obreno.strconsumer.custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

    @SneakyThrows // Remove a necessidade de assinar os métodos com suas determinadas exceções
    @StrConsumerCustomListener(groupId = "group-1")
    public void create(String message) {
       log.info("CREATE ::: Received Message: {}", message);
       throw new IllegalArgumentException("EXCEPTION...");
    }

    @StrConsumerCustomListener(groupId = "group-1")
    public void log(String message) {
        log.info("LOG ::: Received Message: {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void history(String message) {
        log.info("HISTORY ::: Received Message: {}", message);
    }
}
