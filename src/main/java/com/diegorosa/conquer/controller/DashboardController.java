package com.diegorosa.conquer.controller;

import com.diegorosa.common.MaskUtil;
import com.diegorosa.conquer.entity.Contrato;
import com.diegorosa.conquer.model.dto.ContratoListResultDTO;
import com.diegorosa.conquer.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ContractService service;

    @GetMapping("/")
    public String relatorio(Model model) {
        List<Contrato> contratos = service.findAllOrderedByDataAssinaturaDesc(50);

        List<ContratoListResultDTO> dtoList = new ArrayList<>();
        MaskUtil maskformatter = new MaskUtil();
        contratos.forEach(contrato -> {
            ContratoListResultDTO dto = new ContratoListResultDTO();
            dto.setCnpjFormatado(maskformatter.maskCnpj(String.valueOf(contrato.getCnpjContratada())));
            dto.setDataAssinaturaFormatada(contrato.getDataAssinatura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            dto.setOrigemLicitacao(contrato.getOrigemLicitacao());
            dto.setValorFormatado(maskformatter.maskMoedaReais(contrato.getValorInicial()));
            dtoList.add(dto);
        });

        model.addAttribute("contratos", dtoList);
        return "index";
    }

}
