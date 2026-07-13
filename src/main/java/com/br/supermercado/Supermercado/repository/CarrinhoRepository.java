package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
