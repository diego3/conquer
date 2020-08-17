package com.diegorosa.conquer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ConquerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConquerApplication.class, args);
	}

	@GetMapping("/")
	public String relatorio(Model model) {
		model.addAttribute("name", "Diego");
		return "index";
	}

}
