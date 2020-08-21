package com.diegorosa.conquer.model;

public enum ContractReportTypeEnum {
    CNPJ("cnpj"),
    ASSINATURA("assinatura"),
    ORIGEM("origem");

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
