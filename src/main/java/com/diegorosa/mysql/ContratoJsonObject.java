package com.diegorosa.mysql;

public class ContratoJsonObject {
    private String identificador;
    private Integer uasg;
    private Integer modalidade_licitacao;
    private Integer numero_aviso_licitacao;
    private Integer codigo_contrato;
    private String licitacao_associada;
    private String origem_licitacao;
    private Integer numero;
    private Integer numero_aditivo;
    private String numero_processo;
    private String cpfContratada;
    private String cnpj_contratada;
    private String data_assinatura;
    private String fundamento_legal;
    private String data_inicio_vigencia;
    private String data_termino_vigencia;
    private Double valor_inicial;

    public String getIdentificador() {
        return identificador;
    }

    public Integer getUasg() {
        return uasg;
    }

    public Integer getModalidadeLicitacao() {
        return modalidade_licitacao;
    }

    public Integer getNumeroAvisoLicitacao() {
        return numero_aviso_licitacao;
    }

    public Integer getCodigoContrato() {
        return codigo_contrato;
    }

    public String getLicitacaoAssociada() {
        return licitacao_associada;
    }

    public String getOrigemLicitacao() {
        return origem_licitacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getNumeroAditivo() {
        return numero_aditivo;
    }

    public String getNumeroProcesso() {
        return numero_processo;
    }

    public String getCpfContratada() {
        return cpfContratada;
    }

    public String getCnpjContratada() {
        return cnpj_contratada;
    }

    public String getDataAssinatura() {
        return data_assinatura;
    }

    public String getFundamentoLegal() {
        return fundamento_legal;
    }

    public String getDataInicioVigencia() {
        return data_inicio_vigencia;
    }

    public String getDataTerminoVigencia() {
        return data_termino_vigencia;
    }

    public Double getValorInicial() {
        return valor_inicial;
    }
}
