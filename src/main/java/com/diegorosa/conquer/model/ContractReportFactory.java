package com.diegorosa.conquer.model;

import com.diegorosa.conquer.entity.Contrato;

import java.io.Writer;
import java.util.List;

public class ContractReportFactory {

    public static CsvReport create(String tipo, List<Contrato> contracts, Writer writer) {
        ContractReportTypeEnum type = ContractReportTypeEnum.valueOf(tipo.toUpperCase());

        switch (type) {
            case CNPJ:
                return new ContractReportByCnpj(contracts, writer);
            case ORIGEM:
                return new ContractReportByOrigem(contracts, writer);
            case ASSINATURA:
                return new ContractReportByCnpj(contracts, writer);
            default:
                throw new RuntimeException(String.format("O tipo [%s] de relatório é inválido", tipo));
        }
    }
}
