package dev.obreno.strconsumer.custom;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Anotação para a JVM de que essa interface precisa estar disponível como reflexão em tempo de execução
@Target(ElementType.METHOD) // Meta-anotação
@KafkaListener
public @interface StrConsumerCustomListener {

    // Define o tópico automatiamente e de forma padrão como "str-topic"
    @AliasFor(annotation = KafkaListener.class, attribute = "topics")
    String[] topics() default "str-topic";

    // Define o containerFactory automatiamente e de forma padrão como "strContainerFactory"
    @AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
    String containerFactory() default "strContainerFactory";

    @AliasFor(annotation = KafkaListener.class, attribute = "groupId")
    String groupId() default "";
}
