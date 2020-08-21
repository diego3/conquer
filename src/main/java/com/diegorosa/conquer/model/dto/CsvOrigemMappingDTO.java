package com.diegorosa.conquer.model.dto;

public class CsvOrigemMappingDTO {
    private String origem;
    private String valor;

    public CsvOrigemMappingDTO(String label, String value) {
        this.origem = label;
        this.valor = value;
    }
}
