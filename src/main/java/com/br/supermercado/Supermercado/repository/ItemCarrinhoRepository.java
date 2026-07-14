package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
    // Verificar se o produto já existe no carrinho
    Optional<ItemCarrinho> findByCarrinhoAndProdutoId(Long carrinhoId, Long produtoId);
}
