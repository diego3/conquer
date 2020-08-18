package com.diegorosa.conquer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String relatorio(Model model) {
        model.addAttribute("name", "Diego");
        return "index";
    }

}
