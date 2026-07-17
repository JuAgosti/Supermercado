package com.br.supermercado.Supermercado.service;

import com.br.supermercado.Supermercado.model.Carrinho;
import com.br.supermercado.Supermercado.model.ItemCarrinho;
import com.br.supermercado.Supermercado.model.enums.StatusCarrinho;
import com.br.supermercado.Supermercado.repository.CarrinhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarrinhoAgendadorService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoService produtoService;

    public CarrinhoAgendadorService(CarrinhoRepository carrinhoRepository, ProdutoService produtoService){
        this.carrinhoRepository = carrinhoRepository;
        this.produtoService = produtoService;
    }

    // Buscar todos os carrinhos Abertos que foram criados a mais de 15 minutos
    @Scheduled(fixedRate = 60000) // Execute a cada 1 minuto(60000 milissegundo) para verificar abandonos
    @Transactional
    public void liberarCarrinhoAbandonados(){
        // Define o tempo limite
        LocalDateTime limiteTempo = LocalDateTime.now().minusMinutes(15);

        // Buscar carrinhos abertos criados antes do tempo limite
        List<Carrinho> carrinhosAbandonados = carrinhoRepository
                .findByStatusCarrinhoAndDataCriacaoBefore(StatusCarrinho.ABERTO, limiteTempo);

        if(carrinhosAbandonados.isEmpty()){
            return; // Se não houver carrinhos abandonados não faz nada
        }

        System.out.println("Limpeza de Carrinhos Abandonados: Iniciando devolução de " + carrinhosAbandonados.size() + " carrinho(s) abandonado(s)!");

        for (Carrinho carrinho : carrinhosAbandonados){
            for(ItemCarrinho itemCarrinho : carrinho.getItens()){
                produtoService.darEntradaEsotque(itemCarrinho.getProduto().getId() , itemCarrinho.getQuantidade());
                System.out.println("Devolvendo ao estoque: " + itemCarrinho.getQuantidade() + " x " + itemCarrinho.getProduto());
            }

            // Ajustar status do carrinho para EXPIRADO
            carrinho.setStatusCarrinho(StatusCarrinho.EXPIRADO);
            carrinhoRepository.save(carrinho);
        }

        System.out.println("Limpeza de carrinhos concluída!");
    }
}
