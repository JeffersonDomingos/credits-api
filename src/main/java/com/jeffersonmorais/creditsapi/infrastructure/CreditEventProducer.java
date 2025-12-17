package com.jeffersonmorais.creditsapi.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffersonmorais.creditsapi.domain.CreditConsultedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditEventProducer {

    private static final String TOPIC = "credit-events";

    private final KafkaTemplate<String, CreditConsultedEvent> kafkaTemplate;

    public void publish(CreditConsultedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
