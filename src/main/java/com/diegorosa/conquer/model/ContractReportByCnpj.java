package com.diegorosa.conquer.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.Writer;

import com.diegorosa.common.MaskUtil;
import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.model.dto.CsvCnpjMappingDTO;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ContractReportByCnpj implements CsvReport {
    private static final Logger logger = LoggerFactory.getLogger(ContractReportByCnpj.class);

    private List<Contrato> contratos;
    private Writer writer;
    private Function<Contrato, Long> groupFunction;
    private MaskUtil formatter;

    public ContractReportByCnpj(List<Contrato> contratos, Writer writer) {
        this.contratos = contratos;
        this.writer = writer;
        this.groupFunction = Contrato::getCnpjContratada;
        this.formatter = new MaskUtil();
    }

    public void generate() {
        Map<Long, Double> sum = contratos.stream()
                .collect(Collectors.groupingBy(groupFunction,
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

    protected <T> void generateCsv(ArrayList<T> result) {
        try {
            StatefulBeanToCsv<T> builder = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(';')
                    .build();
            builder.write(result);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            logger.error(e.getMessage());
        }
    }
}
