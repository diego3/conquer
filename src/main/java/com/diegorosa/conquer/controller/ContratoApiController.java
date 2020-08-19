package com.diegorosa.conquer.controller;

import com.diegorosa.common.MaskUtil;
import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.model.dto.DataAssinaturaResultDTO;
import com.diegorosa.conquer.service.ContratoService;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

class AgregadoCnpj {
    public String cnpj;
    public String valor;
}

class AgregadoOrigem {
    public String origem;
    public String valor;
}

class AgregadoAssinatura {
    public String dataAssinatura;
    public String valor;
    public LocalDate dateAssinatura;

    public LocalDate getDateAssinatura() {
        return dateAssinatura;
    }
}

@RestController
@RequestMapping("/api/contrato")
public class ContratoApiController {
    private static final Logger logger = LoggerFactory.getLogger(ContratoApiController.class);

    @Autowired
    private ContratoService service;

    @PostMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTableContrato() {
        try {
            service.importaContratos();
        } catch (Exception e) {
            String body = String.format("{\"success\":false, \"error\":\"%s\"}", e.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("{\"success\": true}", HttpStatus.OK);
    }

    @GetMapping(value="/csv/{type}", produces = "text/csv")
    public void downloadCsv(@PathVariable String type, HttpServletResponse response) {

    }

    @GetMapping(value= {"/chart", "/chart/{limit}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DataAssinaturaResultDTO> chartData(@PathVariable(required = false) Integer limit) {
        if (limit == null || limit == 0) {
            limit = 1000;
        }
        return service.findGroupedByDataAssinatura(limit);
    }

    @GetMapping(value = "/csv/cnpj", produces = "text/csv")
    public void downloadCsvPorCnpj(HttpServletResponse response) {
        List<Contrato> contratos = service.findAll();

        Map<Long, Double> sum = contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getCnpjContratada,
                            Collectors.summingDouble(Contrato::getValorInicial)));

        DecimalFormat reaisFormat = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        ArrayList<AgregadoCnpj> groupedBy = new ArrayList<>();
        sum.forEach((key, value) -> {
            AgregadoCnpj newAg = new AgregadoCnpj();
            newAg.cnpj = new MaskUtil().maskCnpj(String.valueOf(key));
            newAg.valor = reaisFormat.format(value);
            groupedBy.add(newAg);
        });

        try {
             StatefulBeanToCsv<AgregadoCnpj> builder = new StatefulBeanToCsvBuilder<AgregadoCnpj>(response.getWriter())
                     .withSeparator(';')
                     .build();
             builder.write(groupedBy);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    @GetMapping(value = "/csv/origem", produces = "text/csv")
    public void downloadCsvPorOrigem(HttpServletResponse response) {
        List<Contrato> contratos = service.findAll();

        Map<String, Double> sum = contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getOrigemLicitacao,
                        Collectors.summingDouble(Contrato::getValorInicial)));

        DecimalFormat reaisFormat = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        ArrayList<AgregadoOrigem> groupedBy = new ArrayList<>();
        sum.forEach((key, value) -> {
            AgregadoOrigem newAg = new AgregadoOrigem();
            newAg.origem = key;
            newAg.valor = reaisFormat.format(value);
            groupedBy.add(newAg);
        });

        try {
            StatefulBeanToCsv<AgregadoOrigem> builder = new StatefulBeanToCsvBuilder<AgregadoOrigem>(response.getWriter())
                    .withSeparator(';')
                    .build();
            builder.write(groupedBy);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    @GetMapping(value = "/csv/assinatura", produces = "text/csv")
    public void downloadCsvPorAssinatura(HttpServletResponse response) {
        List<Contrato> contratos = service.findAll();

        Map<LocalDate, Double> sum = contratos.stream()
                .collect(Collectors.groupingBy(Contrato::getDataAssinatura,
                        Collectors.summingDouble(Contrato::getValorInicial)));

        DecimalFormat reaisFormat = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        ArrayList<AgregadoAssinatura> groupedBy = new ArrayList<>();
        sum.forEach((key, value) -> {
            AgregadoAssinatura newAg = new AgregadoAssinatura();
            newAg.dataAssinatura = key.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            newAg.valor = reaisFormat.format(value);
            newAg.dateAssinatura = key;
            groupedBy.add(newAg);
        });

        groupedBy.sort(Comparator.comparing(AgregadoAssinatura::getDateAssinatura).reversed());

        try {
            ColumnPositionMappingStrategy<AgregadoAssinatura> mappingStrategy = new ColumnPositionMappingStrategy<>();
            mappingStrategy.setType(AgregadoAssinatura.class);
            String[] columns = new String[] {"dataAssinatura", "valor"};
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<AgregadoAssinatura> builder = new StatefulBeanToCsvBuilder<AgregadoAssinatura>(response.getWriter())
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .build();
            builder.write(groupedBy);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            logger.error(e.getMessage());
        }
    }
}
