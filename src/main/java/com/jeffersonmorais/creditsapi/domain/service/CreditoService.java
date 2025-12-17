package com.jeffersonmorais.creditsapi.domain.service;

import com.jeffersonmorais.creditsapi.domain.CreditConsultedEvent;
import com.jeffersonmorais.creditsapi.domain.entity.Credito;
import com.jeffersonmorais.creditsapi.domain.repository.CreditoRepository;
import com.jeffersonmorais.creditsapi.exception.CreditNotFoundException;
import com.jeffersonmorais.creditsapi.exception.NfesNotFoundException;
import com.jeffersonmorais.creditsapi.infrastructure.CreditEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    @Autowired
    private CreditEventProducer creditEventProducer;

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        List<Credito> nfes = creditoRepository.findByNumeroNfse(numeroNfse);

        if (nfes.isEmpty()) {
            throw new NfesNotFoundException(numeroNfse);
        }

        creditEventProducer.publish(new CreditConsultedEvent("NFSE", numeroNfse, LocalDateTime.now()));

        return nfes;

    }

    public Credito findByNumeroCredito(String numeroCredito) {

        Credito credito = creditoRepository.findByNumeroCredito(numeroCredito).orElseThrow(() -> new CreditNotFoundException(numeroCredito));

        creditEventProducer.publish(new CreditConsultedEvent("Credito", numeroCredito, LocalDateTime.now()));

        return credito;

    }

}
