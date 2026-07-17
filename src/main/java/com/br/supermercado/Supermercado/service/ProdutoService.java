package com.br.supermercado.Supermercado.service;

import com.br.supermercado.Supermercado.model.Produto;
import com.br.supermercado.Supermercado.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    // Cadastrar um produto
    public Produto cadastro(Produto produto){

        // Verifica data de validade do produto
        if(produto.getDataValidade() != null && produto.getDataValidade().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Não é possível cadastrar um produto vencido!");
        }

        // Verifcar se o preço do produto seja maior que zero
        if(produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero!");
        }

        return produtoRepository.save(produto);
    }

    // Entrada de mercadoria
    public Produto darEntradaEsotque(Long produtoId, Integer quantidadeAdicionada){

        // Verifica se a quantidade é maior ou menor que zero
        if(quantidadeAdicionada == null || quantidadeAdicionada <= 0){
            throw new IllegalArgumentException("A quantidade a ser adicionada deve ser maior que zero!");
        }

        // Buscar se o produto já existe
        Produto produto = produtoRepository.findById(produtoId)
                            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + produtoId));

        // Adicionar produto ao estoque
        int novoEsotuqe = produto.getQuantidadeEstoque() + quantidadeAdicionada;
        produto.setQuantidadeEstoque(novoEsotuqe);

        return produtoRepository.save(produto);
    }

    // Saída de mercadoria
    public Produto darBaixaEstoque(Long produtoId, Integer quantidadeRetirada){

        // Verifica se a quantidade é maior ou menor que zero
        if(quantidadeRetirada == null || quantidadeRetirada <= 0){
            throw new IllegalArgumentException("A quantidade retirada deve ser maior que zero!");
        }

        // Buscar o produto
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));

        // Verificar se tem estoque
        if(produto.getQuantidadeEstoque() < quantidadeRetirada){
            throw new IllegalArgumentException("Estoque insuficente para o produto: " + produto.getNome()
                    + "(Disponível: " + produto.getQuantidadeEstoque() + ")");
        }

        // Reduzir do estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidadeRetirada);

        return produtoRepository.save(produto);

    }

    // Listar produtos
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
}
