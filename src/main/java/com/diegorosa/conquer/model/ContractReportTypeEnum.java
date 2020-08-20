package com.diegorosa.conquer.model;

public enum ContractReportTypeEnum {
    CNPJ("cnpj"),
    DATA_ASSINATURA("assinatura"),
    ORIGEM_LICITACAO("origem");

    private String type;

    ContractReportTypeEnum(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType(String type) {
        return type;
    }
}
