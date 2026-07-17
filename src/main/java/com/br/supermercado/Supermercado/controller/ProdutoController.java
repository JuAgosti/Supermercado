package com.br.supermercado.Supermercado.controller;

import com.br.supermercado.Supermercado.model.Produto;
import com.br.supermercado.Supermercado.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    // Cadastrar produtos
    @PostMapping
    public ResponseEntity <Produto> cadastrarProduto(@Valid @RequestBody Produto produto){
        Produto novoProduto = produtoService.cadastro(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    // Dar entrada no estoque
    @PatchMapping("/{id}/adicionar-estoque")
    public ResponseEntity<Produto> adicionarEstoque(
            @PathVariable Long id,
            @RequestParam Integer quantidade){
        Produto produtoAtualizado = produtoService.darEntradaEsotque(id, quantidade);
        return ResponseEntity.ok(produtoAtualizado);
    }

    // Dar saida do estoque
    @PatchMapping("/{id}/remover-estoque")
    public ResponseEntity<Produto> removerEstoque(
            @PathVariable Long id,
            @RequestParam Integer quantidade){
        Produto produtoAtualizado = produtoService.darBaixaEstoque(id, quantidade);
        return ResponseEntity.ok(produtoAtualizado);
    }

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }
}
