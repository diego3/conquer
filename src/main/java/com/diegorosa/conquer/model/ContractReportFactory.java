package com.diegorosa.conquer.model;

public class ContractReportFactory {

    public static void createFromString(String tipo) {
        ContractReportTypeEnum type = ContractReportTypeEnum.valueOf(tipo);

        switch (type) {
            case CNPJ:
                new ContractReportByCnpj();
                break;
            case ORIGEM_LICITACAO:
                new ContractReportByCnpj();
                break;
            case DATA_ASSINATURA:
                new ContractReportByCnpj();
                break;
        }
    }
}
