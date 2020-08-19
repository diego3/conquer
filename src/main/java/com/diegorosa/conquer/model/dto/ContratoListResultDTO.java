package com.diegorosa.conquer.model.dto;

public class ContratoListResultDTO {
    private String cnpjFormatado;
    private String dataAssinaturaFormatada;
    private String origemLicitacao;
    private String valorFormatado;

    public String getCnpjFormatado() {
        return cnpjFormatado;
    }

    public void setCnpjFormatado(String cnpjFormatado) {
        this.cnpjFormatado = cnpjFormatado;
    }

    public String getDataAssinaturaFormatada() {
        return dataAssinaturaFormatada;
    }

    public void setDataAssinaturaFormatada(String dataAssinaturaFormatada) {
        this.dataAssinaturaFormatada = dataAssinaturaFormatada;
    }

    public String getOrigemLicitacao() {
        return origemLicitacao;
    }

    public void setOrigemLicitacao(String origemLicitacao) {
        this.origemLicitacao = origemLicitacao;
    }

    public String getValorFormatado() {
        return valorFormatado;
    }

    public void setValorFormatado(String valorFormatado) {
        this.valorFormatado = valorFormatado;
    }
}
