package com.br.supermercado.Supermercado.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@Setter
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente") // nulo caso o cliente não queria colocar o cpf
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    @Column(name = "valor_total",nullable = false, precision = 10, scale = 2)
    private BigDecimal ValorTotal;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;

    @Column(name ="forma_pagamento", nullable = false, length = 50)
    private String formaPagamento; // EX: DINHEIRO, PIX, CARTAO_CREDITO
}
