package com.diegorosa.conquer.controller;

import com.diegorosa.conquer.model.ContractReportFactory;
import com.diegorosa.conquer.model.CsvReport;
import com.diegorosa.conquer.model.dto.DataAssinaturaResultDTO;
import com.diegorosa.conquer.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/contrato")
public class ContractApiController {
    private static final Logger logger = LoggerFactory.getLogger(ContractApiController.class);

    @Autowired
    private ContractService service;

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

    @GetMapping(value = "/csv/{type}", produces = "text/csv")
    public void downloadCsv(@PathVariable String type, HttpServletResponse response) {
        CsvReport report = null;
        try {
            report = ContractReportFactory.create(type, service.findAll(), response.getWriter());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        assert report != null;
        report.generate();
    }

    @GetMapping(value = {"/chart", "/chart/{limit}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DataAssinaturaResultDTO> chartData(@PathVariable(required = false) Integer limit) {
        if (limit == null || limit == 0) {
            limit = 1000;
        }
        return service.findGroupedByDataAssinatura(limit);
    }
}
