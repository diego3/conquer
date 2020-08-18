package com.diegorosa.conquer.controller;

import com.diegorosa.conquer.service.LicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contrato")
public class ContratoApiController {

    @Autowired
    private LicitacaoService service;

    @PostMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTableContrato() {
        try {
            service.importaContratos();
        } catch (Exception e) {
            String body = String.format("{\"success\":false, \"error\":\"%s\"}", e.getMessage());
            return new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("{\"success\": true}", HttpStatus.OK);
    }
}
