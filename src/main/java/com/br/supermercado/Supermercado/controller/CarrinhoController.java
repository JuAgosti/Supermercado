package com.br.supermercado.Supermercado.controller;

import com.br.supermercado.Supermercado.model.Carrinho;
import com.br.supermercado.Supermercado.model.Cliente;
import com.br.supermercado.Supermercado.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService){
        this.carrinhoService = carrinhoService;
    }

    // Criar um novo carrinho
    @PostMapping
    public ResponseEntity<Carrinho> criarCarrinho(@RequestParam Long clienteId){
        Carrinho novoCarrinho = carrinhoService.novoCarrinho(clienteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarrinho);
    }

    // Adicionar produtos
    @PostMapping("/{carrinhoId}/adicionar")
    public ResponseEntity<Carrinho> adicionarProduto(
            @PathVariable Long carrinhoId,
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade){

        Carrinho carrinhoAtualizado = carrinhoService.adicionarProduto(carrinhoId,produtoId, quantidade);
        return ResponseEntity.ok(carrinhoAtualizado);
    }

    // Remover produtos
    @PostMapping("/{carrinhoId}/remover")
    public ResponseEntity<Carrinho> removerProduto(
            @PathVariable Long carrinhoId,
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade){

        Carrinho carrinhoAtualizado = carrinhoService.removerProduto(carrinhoId,produtoId, quantidade);
        return ResponseEntity.ok(carrinhoAtualizado);
    }

    // Ver os carrinhos
    @GetMapping
    public ResponseEntity<List<Carrinho>> listarCarrinhos(){
        List<Carrinho> carrinhos = carrinhoService.listarCarrinho();
        return ResponseEntity.ok(carrinhos);
    }
}
