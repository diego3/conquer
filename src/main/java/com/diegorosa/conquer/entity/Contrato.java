package com.diegorosa.conquer.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;
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
    private Integer cnpjContratada;
    private Date dataAssinatura;
    private String fundamentoLegal;
    private Date dataInicioVigencia;
    private Date dataTerminoVigencia;
    private Double valorInicial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
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

    public Integer getCnpjContratada() {
        return cnpjContratada;
    }

    public void setCnpjContratada(Integer cnpjContratada) {
        this.cnpjContratada = cnpjContratada;
    }

    public Date getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(Date dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public String getFundamentoLegal() {
        return fundamentoLegal;
    }

    public void setFundamentoLegal(String fundamentoLegal) {
        this.fundamentoLegal = fundamentoLegal;
    }

    public Date getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(Date dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public Date getDataTerminoVigencia() {
        return dataTerminoVigencia;
    }

    public void setDataTerminoVigencia(Date dataTerminoVigencia) {
        this.dataTerminoVigencia = dataTerminoVigencia;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }
}
