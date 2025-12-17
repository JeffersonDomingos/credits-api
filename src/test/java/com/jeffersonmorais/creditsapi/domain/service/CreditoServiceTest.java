package com.jeffersonmorais.creditsapi.domain.service;

import com.jeffersonmorais.creditsapi.domain.entity.Credito;
import com.jeffersonmorais.creditsapi.domain.repository.CreditoRepository;
import com.jeffersonmorais.creditsapi.exception.CreditNotFoundException;
import com.jeffersonmorais.creditsapi.exception.NfesNotFoundException;
import com.jeffersonmorais.creditsapi.infrastructure.CreditEventProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private CreditEventProducer creditEventProducer;

    @InjectMocks
    private CreditoService creditoService;

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
    void shouldReturnListOfCreditosWhenNumeroNfseExists() {
        String numeroNfse = "7891011";
        List<Credito> creditos = List.of(buildCredito());

        when(creditoRepository.findByNumeroNfse(numeroNfse))
                .thenReturn(creditos);

        List<Credito> result = creditoService.findByNumeroNfse(numeroNfse);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(creditoRepository).findByNumeroNfse(numeroNfse);
        verify(creditEventProducer, times(1)).publish(any());
    }

    @Test
    void shouldThrowExceptionWhenNumeroNfseNotFound() {

        String numeroNfse = "000000";

        when(creditoRepository.findByNumeroNfse(numeroNfse))
                .thenReturn(List.of());


        assertThrows(NfesNotFoundException.class, () ->
                creditoService.findByNumeroNfse(numeroNfse)
        );

        verify(creditoRepository).findByNumeroNfse(numeroNfse);
        verify(creditEventProducer, never()).publish(any());

    }

    @Test
    void shouldReturnCreditoWhenNumeroCreditoExists() {

        String numeroCredito = "123456";
        Credito credito = buildCredito();

        when(creditoRepository.findByNumeroCredito(numeroCredito))
                .thenReturn(Optional.of(credito));


        Credito result = creditoService.findByNumeroCredito(numeroCredito);


        assertNotNull(result);
        assertEquals(numeroCredito, result.getNumeroCredito());

        verify(creditoRepository).findByNumeroCredito(numeroCredito);
        verify(creditEventProducer, times(1)).publish(any());
    }

    @Test
    void shouldThrowExceptionWhenNumeroCreditoNotFound() {

        String numeroCredito = "000000";

        when(creditoRepository.findByNumeroCredito(numeroCredito))
                .thenReturn(Optional.empty());


        assertThrows(CreditNotFoundException.class, () ->
                creditoService.findByNumeroCredito(numeroCredito)
        );

        verify(creditoRepository).findByNumeroCredito(numeroCredito);
        verify(creditEventProducer, never()).publish(any());

    }
}


