package com.diegorosa.conquer.model.dto;

import java.time.LocalDate;

public class DataAssinaturaResultDTO {
    private LocalDate dataAssinatura;
    private Double valorSomatoria;

    public DataAssinaturaResultDTO(LocalDate dataAssinatura, Double valor) {
        this.dataAssinatura = dataAssinatura;
        this.valorSomatoria = valor;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }

    public Double getValorSomatoria() {
        return valorSomatoria;
    }
}
