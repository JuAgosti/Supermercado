package com.br.supermercado.Supermercado.repository;

import com.br.supermercado.Supermercado.model.Cliente;
import com.br.supermercado.Supermercado.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
