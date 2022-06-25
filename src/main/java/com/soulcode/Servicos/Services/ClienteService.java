package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.Cliente;
import com.soulcode.Servicos.Models.Funcionario;
import com.soulcode.Servicos.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> mostrarTodosCliente(){

        return clienteRepository.findAll();
    }

    public Cliente mostrarClientePeloId(Integer idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        return cliente.orElseThrow();
    }

    public Cliente mostrarClientePeloEmail(String email) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        return cliente.orElseThrow();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);
    }

    public void excluirCliente(Integer idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    public Cliente editarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
