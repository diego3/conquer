package com.diegorosa.conquer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.Writer;
import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.model.dto.CsvCnpjMappingDTO;

public class ContractReportByCnpj extends AbstractReport {
    public ContractReportByCnpj(List<Contrato> contratos, Writer writer) {
        super(contratos, writer);
    }

    public void generate() {
        Map<Long, Double> sum = contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getCnpjContratada,
                        Collectors.summingDouble(Contrato::getValorInicial)));

        ArrayList<CsvCnpjMappingDTO> resultList = new ArrayList<>();
        sum.forEach((key, value) -> {
            resultList.add(new CsvCnpjMappingDTO(
                    formatter.maskCnpj(String.valueOf(key)),
                    formatter.maskMoedaReais(value)
            ));
        });

        generateCsv(resultList);
    }
}
