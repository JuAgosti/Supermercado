package com.br.supermercado.Supermercado.service;

import com.br.supermercado.Supermercado.model.Carrinho;
import com.br.supermercado.Supermercado.model.ItemCarrinho;
import com.br.supermercado.Supermercado.model.Produto;
import com.br.supermercado.Supermercado.model.Venda;
import com.br.supermercado.Supermercado.model.enums.StatusCarrinho;
import com.br.supermercado.Supermercado.repository.CarrinhoRepository;
import com.br.supermercado.Supermercado.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;
    private final CarrinhoRepository carrinhoRepository;

    public VendaService (VendaRepository vendaRepository,CarrinhoRepository carrinhoRepository){
        this.vendaRepository =vendaRepository;
        this.carrinhoRepository = carrinhoRepository;
    }

    // Receber o Carrinho
    @Transactional
    public Venda finalizarVenda(Long carrinhoId, String formaPagamento){
        // Buscar se o carrinho existe
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(()-> new IllegalArgumentException("Carrinho não encontrado!"));

        // Verifica status carrinho
        if(carrinho.getStatusCarrinho() != StatusCarrinho.ABERTO){
            throw new IllegalStateException("Apenas carrinhos ABERTOS podem ser finalizados!");
        }

        // Verificar se tem itens
        if(carrinho.getItens() == null || carrinho.getItens().isEmpty()){
            throw new IllegalArgumentException("Não é possível finalizar uma venda com carrinho vazio!");
        }

        // Validar a forma de pagamento
        if(formaPagamento == null || carrinho.getItens().isEmpty()){
            throw new IllegalStateException("A forma de pagamento deve ser informada!");
        }

        // Calcular o valor total da compra
        BigDecimal valorTotal = BigDecimal.ZERO;

        for(ItemCarrinho item : carrinho.getItens()) {
            BigDecimal precoProduto = item.getProduto().getPreco();
            ;
            // Não pode misturar dados diferentes em operações, faz a conversão para BigDecimal
            BigDecimal quantidade = new BigDecimal(item.getQuantidade());

            // Calculando o subtotal
            BigDecimal subtotal = precoProduto.multiply(quantidade);
            //Soma de tudo
            valorTotal = valorTotal.add(subtotal);
        }
            // Ao passar no caixa mudar status do carrinho para fechado
            carrinho.setStatusCarrinho(StatusCarrinho.FINALIZADO);
            carrinhoRepository.save(carrinho);

            // Comprovante da venda

            Venda venda = new Venda();
            venda.setCliente(carrinho.getCliente());
            venda.setValorTotal(valorTotal);
            venda.setDataVenda(LocalDateTime.now());
            venda.setFormaPagamento(formaPagamento.toUpperCase());

            return vendaRepository.save(venda);
    }

    // Listar vendas
    public List<Venda> listarVendas(){
        return vendaRepository.findAll();
    }
}
