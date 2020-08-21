package com.diegorosa.conquer.model;

import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.model.dto.CsvAssinaturaMappingDTO;

import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContractReportByAssinatura extends AbstractReport {
    public ContractReportByAssinatura(List<Contrato> contratos, Writer writer) {
        super(contratos, writer);
    }

    public void generate() {
        Map<LocalDate, Double> sum = contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getDataAssinatura,
                        Collectors.summingDouble(Contrato::getValorInicial)));

        ArrayList<CsvAssinaturaMappingDTO> resultList = new ArrayList<>();
        sum.forEach((key, value) -> {
            resultList.add(new CsvAssinaturaMappingDTO(
                    key.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    formatter.maskMoedaReais(value)
            ));
        });

        generateCsv(resultList);
    }
}
