package com.br.supermercado.Supermercado.service;

import com.br.supermercado.Supermercado.model.Cliente;
import com.br.supermercado.Supermercado.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    // Cadastrar cliente
    public Cliente cadastrarCliente(Cliente cliente){

        // Verifica se o CPF foi preenchido
        if(cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()){
            throw new IllegalArgumentException("O CPF do cliente é obrigatório!");
        }

        // Verifica se já existe um cliente com o mesmo CPF
        Boolean cpfExiste = clienteRepository.findByCpf(cliente.getCpf()).isPresent();
        if(cpfExiste){
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF!");
        }

        return clienteRepository.save(cliente);
    }

    // Buscar clientes por CPF
    public Cliente buscaPorCpf(String cpf){
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
}
