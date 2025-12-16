package com.jeffersonmorais.creditsapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffersonmorais.creditsapi.domain.entity.Credito;
import com.jeffersonmorais.creditsapi.domain.service.CreditoService;
import com.jeffersonmorais.creditsapi.exception.CreditNotFoundException;
import com.jeffersonmorais.creditsapi.exception.NfesNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditoController.class)
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService creditoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Credito buildCredito() {
        return Credito.builder()
                .id(1L)
                .numeroCredito("123456")
                .numeroNfse("7891011")
                .dataConstituicao(LocalDate.of(2024, 2, 25))
                .valorIssqn(new BigDecimal("1500.75"))
                .tipoCredito("ISSQN")
                .simplesNacional(true)
                .aliquota(new BigDecimal("5.0"))
                .valorFaturado(new BigDecimal("30000"))
                .valorDeducao(new BigDecimal("5000"))
                .baseCalculo(new BigDecimal("25000"))
                .build();
    }

    @Test
    void shouldReturnListOfCreditosWhenNumeroNfseExists() throws Exception {

        when(creditoService.findByNumeroNfse("7891011"))
                .thenReturn(List.of(buildCredito()));

        mockMvc.perform(get("/api/creditos/7891011"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito").value("123456"))
                .andExpect(jsonPath("$[0].numeroNfse").value("7891011"));
    }

    @Test
    void shouldReturn404WhenNumeroNfseNotFound() throws Exception {

        when(creditoService.findByNumeroNfse("000000"))
                .thenThrow(new NfesNotFoundException("000000"));

        mockMvc.perform(get("/api/creditos/000000"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void shouldReturnCreditoWhenNumeroCreditoExists() throws Exception {

        when(creditoService.findByNumeroCredito("123456"))
                .thenReturn(buildCredito());

        mockMvc.perform(get("/api/creditos/credito/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value("123456"))
                .andExpect(jsonPath("$.numeroNfse").value("7891011"));
    }

    @Test
    void shouldReturn404WhenNumeroCreditoNotFound() throws Exception {

        when(creditoService.findByNumeroCredito("000000"))
                .thenThrow(new CreditNotFoundException("000000"));

        mockMvc.perform(get("/api/creditos/credito/000000"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}

