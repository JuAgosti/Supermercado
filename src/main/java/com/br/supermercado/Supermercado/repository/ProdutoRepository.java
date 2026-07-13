package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.Cliente;
import com.br.supermercado.Supermercado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
