package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.Carrinho;
import com.br.supermercado.Supermercado.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
