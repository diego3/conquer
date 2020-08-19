package com.diegorosa.conquer.controller;

import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ContratoService service;

    @GetMapping("/")
    public String relatorio(Model model) {
        List<Contrato> contratos = service.findAllOrderedByDataAssinaturaDesc(100);

        model.addAttribute("contratos", contratos);
        return "index";
    }

}
