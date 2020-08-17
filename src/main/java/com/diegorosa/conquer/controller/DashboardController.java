package com.diegorosa.conquer.controller;

import com.diegorosa.conquer.service.LicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    @Autowired
    private LicitacaoService service;

    @GetMapping("/")
    public String relatorio(Model model) {
        model.addAttribute("name", "Diego");
        return "index";
    }

    @PostMapping("/contratos-sync")
    public String updateContratos() {
        service.importaContratos();
        return "importacaoContratos executado";
    }
}
