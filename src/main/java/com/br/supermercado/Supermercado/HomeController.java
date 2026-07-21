package com.br.supermercado.Supermercado; // <-- Ajuste para o pacote real do seu projeto

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Servidor rodando e Docker funcionando com sucesso!";
    }
}