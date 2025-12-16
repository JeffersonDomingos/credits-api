package com.jeffersonmorais.creditsapi.api.controller;

import com.jeffersonmorais.creditsapi.domain.entity.Credito;
import com.jeffersonmorais.creditsapi.domain.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<Credito>> findByNumeroNfse(@PathVariable String numeroNfse) {
        List<Credito> creditos = creditoService.findByNumeroNfse(numeroNfse);
        return ResponseEntity.ok(creditos);

    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<Credito> findByNumeroCredito(@PathVariable String numeroCredito) {
        Credito credito = creditoService.findByNumeroCredito(numeroCredito);
        return ResponseEntity.ok(credito);
    }

}
