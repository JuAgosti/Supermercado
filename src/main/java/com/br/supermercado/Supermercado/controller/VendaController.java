package com.br.supermercado.Supermercado.controller;

import com.br.supermercado.Supermercado.model.Produto;
import com.br.supermercado.Supermercado.model.Venda;
import com.br.supermercado.Supermercado.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    private final VendaService vendaService;

    public VendaController(VendaService vendaService){
        this.vendaService = vendaService;
    }

    // Finalizar a venda
    @PostMapping("/finalizar")
    public ResponseEntity<Venda> finalizarVenda(
            @RequestParam Long carrinhoId,
            @RequestParam String formaPagamento){

        Venda novaVenda = vendaService.finalizarVenda(carrinhoId, formaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    // Listar todos as vendas
    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas(){
        List<Venda> venda = vendaService.listarVendas();
        return ResponseEntity.ok(venda);
    }
}
