package com.jeffersonmorais.creditsapi.exception;

public class NfesNotFoundException extends RuntimeException {

    public NfesNotFoundException(String numeroNfes) {
        super("NFES não encontrada para a busca: " + numeroNfes);
    }
}
