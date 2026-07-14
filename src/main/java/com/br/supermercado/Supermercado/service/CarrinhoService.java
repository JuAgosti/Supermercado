package com.br.supermercado.Supermercado.service;

import com.br.supermercado.Supermercado.model.Carrinho;
import com.br.supermercado.Supermercado.model.Cliente;
import com.br.supermercado.Supermercado.model.ItemCarrinho;
import com.br.supermercado.Supermercado.model.Produto;
import com.br.supermercado.Supermercado.model.enums.StatusCarrinho;
import com.br.supermercado.Supermercado.repository.CarrinhoRepository;
import com.br.supermercado.Supermercado.repository.ClienteRepository;
import com.br.supermercado.Supermercado.repository.ItemCarrinhoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutoService produtoService;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemCarrinhoRepository itemCarrinhoRepository,
                                ClienteRepository clienteRepository, ProdutoService produtoService){
        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoService = produtoService; // Injetado service para controle de estoque
    }

    // Criar um novo carrinho
    @Transactional
    public Carrinho novoCarrinho(Long clienteId){
        // Buscar se existe o cliente, se não existir impede de criar o carrinho
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        Carrinho carrinho = new Carrinho();
        carrinho.setCliente(cliente);
        carrinho.setDataCriacao(LocalDateTime.now());
        carrinho.setStatusCarrinho(StatusCarrinho.ABERTO);

        return carrinhoRepository.save(carrinho);
    }

    // Adicionar produtos no carrinho e dar baixa no estoque
    @Transactional
    public Carrinho adicionarProduto(Long carrinhoId, Long produtoId, Integer quantidade){
        // Verificar se a quantidade não é menor que igual a zero
        if (quantidade == null || quantidade <= 0){
            throw new IllegalArgumentException("A quantidade adicionada deve ser maior que zero!");
        }

        // Buscar o carrinho e verificar se ele está aberto
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado!"));

        if(carrinho.getStatusCarrinho() != StatusCarrinho.ABERTO){
            throw new IllegalStateException("Não é possível adicionar itens a um carrinho que não está aberto");
        }

        // Chama a service para dar baixa no esotuqe
        Produto produto = produtoService.darBaixaEstoque(produtoId,quantidade);

        Optional<ItemCarrinho> itemExisteOpt = itemCarrinhoRepository.findByCarrinhoAndProdutoId(carrinhoId, produtoId);

        if(itemExisteOpt.isPresent()){
            // Se já existe, asoam a quantidade
            ItemCarrinho itemExiste = itemExisteOpt.get();
            itemExiste.setQuantidade(itemExiste.getQuantidade() + quantidade);
            itemCarrinhoRepository.save(itemExiste);
        } else {
            // Se não existir, cria um novo ItemCarrinho
            ItemCarrinho novoItem = new ItemCarrinho();
            novoItem.setCarrinho(carrinho);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(quantidade);
            itemCarrinhoRepository.save(novoItem);
        }

        return carrinhoRepository.findById(carrinhoId).get();
    }

    @Transactional
    public Carrinho removerProduto(Long carrinhoId, Long produtoId, Integer quantidade){
        // Verificar se a quantidade não é menor que igual a zero
        if(quantidade == null || quantidade <= 0){
            throw new IllegalArgumentException("A quantidade a remover deve ser maior que zero!");
        }

        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(()-> new IllegalArgumentException("Carrinho não encontrado!"));

        if(carrinho.getStatusCarrinho() != StatusCarrinho.ABERTO){
            throw new IllegalStateException("Não é possível alterar um carrinho fechado ou expirado!");
        }

        // Buscar o item específico no carrinho
        ItemCarrinho item = itemCarrinhoRepository.findByCarrinhoAndProdutoId(carrinhoId, produtoId)
                .orElseThrow(()-> new IllegalArgumentException("Este produto não está no carrinho"));

        // Se o cliente quiser remover mais do que ele em no carrinho irá mostrar um erro
        if(quantidade > item.getQuantidade()){
            throw new IllegalArgumentException("Você não pode remover " + quantidade
                    + " unidades de produto porque só exitem " + item.getQuantidade() + " no seu carrinhp!");
        }

        // Devolver os produtos para o estoque
        produtoService.darEntradaEsotque(produtoId, quantidade);

        // Atualizar a quantidade ou remover o item se zerar
        if(item.getQuantidade().equals(quantidade)){
            itemCarrinhoRepository.delete(item);
            carrinho.getItens().remove(item);
        }else {
            // Se sobrar uma quantidade apenas diminui
            item.setQuantidade(item.getQuantidade() - quantidade);
            itemCarrinhoRepository.save(item);
        }

        return carrinhoRepository.save(carrinho);
    }
}
