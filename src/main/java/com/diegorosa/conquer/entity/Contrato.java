package com.diegorosa.conquer.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrato")
public class Contrato {
    @Id
    private Long identificador;
    private Integer uasg;
    private Integer modalidadeLicitacao;
    private Integer numeroAvisoLicitacao;
    private Integer codigoContrato;
    private String licitacaoAssociada;
    private String origemLicitacao;
    private Integer numero;
    private Integer numeroAditivo;
    private String numeroProcesso;
    private String cpfContratada;
    private Long cnpjContratada;
    private LocalDate dataAssinatura;
    private String fundamentoLegal;
    private LocalDate dataInicioVigencia;
    private LocalDate dataTerminoVigencia;
    private Double valorInicial;

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public Integer getUasg() {
        return uasg;
    }

    public void setUasg(Integer uasg) {
        this.uasg = uasg;
    }

    public Integer getModalidadeLicitacao() {
        return modalidadeLicitacao;
    }

    public void setModalidadeLicitacao(Integer modalidadeLicitacao) {
        this.modalidadeLicitacao = modalidadeLicitacao;
    }

    public Integer getNumeroAvisoLicitacao() {
        return numeroAvisoLicitacao;
    }

    public void setNumeroAvisoLicitacao(Integer numeroAvisoLicitacao) {
        this.numeroAvisoLicitacao = numeroAvisoLicitacao;
    }

    public Integer getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(Integer codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public String getLicitacaoAssociada() {
        return licitacaoAssociada;
    }

    public void setLicitacaoAssociada(String licitacaoAssociada) {
        this.licitacaoAssociada = licitacaoAssociada;
    }

    public String getOrigemLicitacao() {
        return origemLicitacao;
    }

    public void setOrigemLicitacao(String origemLicitacao) {
        this.origemLicitacao = origemLicitacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumeroAditivo() {
        return numeroAditivo;
    }

    public void setNumeroAditivo(Integer numeroAditivo) {
        this.numeroAditivo = numeroAditivo;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getCpfContratada() {
        return cpfContratada;
    }

    public void setCpfContratada(String cpfContratada) {
        this.cpfContratada = cpfContratada;
    }

    public Long getCnpjContratada() {
        return cnpjContratada;
    }

    public void setCnpjContratada(Long cnpjContratada) {
        this.cnpjContratada = cnpjContratada;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(LocalDate dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public String getFundamentoLegal() {
        return fundamentoLegal;
    }

    public void setFundamentoLegal(String fundamentoLegal) {
        this.fundamentoLegal = fundamentoLegal;
    }

    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDate getDataTerminoVigencia() {
        return dataTerminoVigencia;
    }

    public void setDataTerminoVigencia(LocalDate dataTerminoVigencia) {
        this.dataTerminoVigencia = dataTerminoVigencia;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }
}
