package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.Cargo;
import com.soulcode.Servicos.Models.Endereco;
import com.soulcode.Servicos.Repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public List<Endereco> mostrarTodosEnderecos(){

        return enderecoRepository.findAll();
    }
    public Endereco mostrarEnderecoPeloId(Integer idEndereco) {
        Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
        return endereco.orElseThrow();
    }
    public Endereco editarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void deletarEndereco(Integer idEndereco) {
        enderecoRepository.deleteById(idEndereco);
    }
}
