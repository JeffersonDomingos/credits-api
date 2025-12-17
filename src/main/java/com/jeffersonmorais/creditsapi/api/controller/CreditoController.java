package com.jeffersonmorais.creditsapi.api.controller;

import com.jeffersonmorais.creditsapi.api.dto.CreditoResponseDTO;
import com.jeffersonmorais.creditsapi.api.mapper.CreditoMapper;
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
    public ResponseEntity<List<CreditoResponseDTO>> findByNumeroNfse(@PathVariable String numeroNfse) {
        return ResponseEntity.ok(CreditoMapper.toResponseList(creditoService.findByNumeroNfse(numeroNfse)));

    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoResponseDTO> findByNumeroCredito(@PathVariable String numeroCredito) {
        return ResponseEntity.ok(CreditoMapper.creditoToResponseDTO(creditoService.findByNumeroCredito(numeroCredito)));
    }

}
