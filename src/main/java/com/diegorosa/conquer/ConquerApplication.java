package com.diegorosa.conquer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@SpringBootApplication
@Controller
public class ConquerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConquerApplication.class, args);
	}

	@GetMapping("/")
	public String relatorio(Model model) {
		model.addAttribute("name", "Diego");
		return "relatorio1";
	}

}
