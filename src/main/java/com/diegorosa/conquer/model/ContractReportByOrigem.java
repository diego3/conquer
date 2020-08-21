package com.diegorosa.conquer.model;

import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.model.dto.CsvOrigemMappingDTO;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContractReportByOrigem extends AbstractReport {
    public ContractReportByOrigem(List<Contrato> contratos, Writer writer) {
        super(contratos, writer);
    }

    public void generate() {
        ArrayList<CsvOrigemMappingDTO> resultList = new ArrayList<>();
        contratos.stream().collect(
                Collectors.groupingBy(Contrato::getOrigemLicitacao,
                Collectors.summingDouble(Contrato::getValorInicial))
        ).forEach((key, value) -> {
            resultList.add(new CsvOrigemMappingDTO(key, formatter.maskMoedaReais(value)));
        });

        generateCsv(resultList);
    }
}
