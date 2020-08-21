package com.diegorosa.conquer.model.dto;

public class CsvCnpjMappingDTO {
    private String cnpj;
    private String valor;

    public CsvCnpjMappingDTO(String label, String value) {
        this.cnpj = label;
        this.valor = value;
    }
}
