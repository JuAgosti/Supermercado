package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.Cliente;
import com.br.supermercado.Supermercado.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
}
