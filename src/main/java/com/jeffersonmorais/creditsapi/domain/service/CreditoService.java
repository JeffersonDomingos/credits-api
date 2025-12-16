package com.jeffersonmorais.creditsapi.domain.service;

import com.jeffersonmorais.creditsapi.domain.entity.Credito;
import com.jeffersonmorais.creditsapi.domain.repository.CreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse);
    }

    public Credito findByNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito).orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
    }

}
