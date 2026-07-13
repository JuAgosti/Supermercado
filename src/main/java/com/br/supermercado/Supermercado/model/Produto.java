package com.br.supermercado.Supermercado.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 500)
    private String descricao;

    // precison = 10, scale = 2: garente a precisao dos centavos no preço
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    @Column(name = "data_validade")
    private LocalDate dataValidade;
}
