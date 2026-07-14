package com.br.supermercado.Supermercado.model;

import com.br.supermercado.Supermercado.model.enums.StatusCarrinho;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho")
@Getter
@Setter
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //cascade: tudo que acontecer com carrinho acontece com os itens
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemCarrinho> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusCarrinho statusCarrinho = StatusCarrinho.ABERTO;

}
