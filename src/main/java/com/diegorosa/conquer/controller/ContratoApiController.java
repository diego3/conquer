package com.diegorosa.conquer.controller;

import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.service.ContratoService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/contrato")
public class ContratoApiController {

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

    @GetMapping(value = "/csv/cnpj", produces = "text/csv")
    public void downloadCsvPorCnpj(HttpServletResponse response) {

        List<Contrato> contratos = service.findAll();

        try {
             StatefulBeanToCsv<Contrato> builder = new StatefulBeanToCsvBuilder<Contrato>(response.getWriter())
                     .build();
             builder.write(contratos);
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
