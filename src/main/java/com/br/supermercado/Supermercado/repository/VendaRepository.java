package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.Cliente;
import com.br.supermercado.Supermercado.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
