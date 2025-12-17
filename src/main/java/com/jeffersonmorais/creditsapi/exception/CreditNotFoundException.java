package com.jeffersonmorais.creditsapi.exception;

public class CreditNotFoundException extends RuntimeException {

    public CreditNotFoundException(String numeroCredito) {
        super("Crédito não encontrado para a busca: " + numeroCredito);
    }

}
