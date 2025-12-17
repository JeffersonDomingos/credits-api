package com.jeffersonmorais.creditsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditConsultedEvent {

    private String tipoConsulta;
    private String valorConsultado;
    private LocalDateTime timestamp;

}
