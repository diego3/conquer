package com.diegorosa.conquer.controller;

import com.diegorosa.conquer.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ContratoService service;

    @GetMapping("/")
    public String relatorio(Model model) {
        model.addAttribute("name", "Diego");
        return "index";
    }

}
