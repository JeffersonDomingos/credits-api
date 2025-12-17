package com.jeffersonmorais.creditsapi.infrastructure.kafka;

import com.jeffersonmorais.creditsapi.domain.CreditConsultedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CreditEventConsumer {

    @KafkaListener(topics = "credit-events", groupId = "credits-api-group")
    public void consume(CreditConsultedEvent event) {

        System.out.println("Evento Recebido -> Tipo: " + event.getTipoConsulta() + " | Valor: " + event.getValorConsultado() + " | Data: " + event.getTimestamp());

    }

}
