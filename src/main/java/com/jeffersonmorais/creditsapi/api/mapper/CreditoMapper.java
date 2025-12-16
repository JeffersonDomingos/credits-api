package com.jeffersonmorais.creditsapi.api.mapper;

import com.jeffersonmorais.creditsapi.api.dto.CreditoResponseDTO;
import com.jeffersonmorais.creditsapi.domain.entity.Credito;

import java.util.List;

public class CreditoMapper {

    private CreditoMapper() {

    }

    public static CreditoResponseDTO creditoToResponseDTO(Credito credito) {
        return new CreditoResponseDTO(
                credito.getNumeroCredito(),
                credito.getNumeroNfse(),
                credito.getDataConstituicao(),
                credito.getValorIssqn(),
                credito.getTipoCredito(),
                credito.isSimplesNacional(),
                credito.getAliquota(),
                credito.getValorFaturado(),
                credito.getValorDeducao(),
                credito.getBaseCalculo()
        );
    }

    public static List<CreditoResponseDTO> toResponseList(List<Credito> creditos) {
        return creditos.stream()
                .map(CreditoMapper::creditoToResponseDTO)
                .toList();
    }

}
