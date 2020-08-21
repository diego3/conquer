package com.diegorosa.conquer.model.dto;

public class CsvAssinaturaMappingDTO {
    private String dataAssinatura;
    private String valor;

    public CsvAssinaturaMappingDTO(String label, String value) {
        this.dataAssinatura = label;
        this.valor = value;
    }
}
