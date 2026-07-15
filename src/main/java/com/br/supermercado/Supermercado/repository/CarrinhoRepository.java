package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.Carrinho;
import com.br.supermercado.Supermercado.model.enums.StatusCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    // Busca carrinhos por status que foram criados antes do horário limite de 15 minutos
    List<Carrinho> findByStatusCarrinhoAndDataCriacaoBefore(StatusCarrinho statusCarrinho, LocalDateTime limiteTempo);
}
