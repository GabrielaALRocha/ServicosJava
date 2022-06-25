package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import com.soulcode.Servicos.Repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//quando se fala em serviços, estamos falando dos métodos do crud da tabela
@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<Funcionario> mostrarTodosFuncionarios() {

        return funcionarioRepository.findAll();
    }

    public Funcionario mostrarFuncionarioPeloId(Integer idFuncionario) {

        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
        return funcionario.orElseThrow();
    }

    public Funcionario mostrarFuncionarioPeloEmail(String email) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
        return funcionario.orElseThrow();
    }

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        funcionario.setIdFuncionario(null);
        return funcionarioRepository.save(funcionario);
    }

    public void excluirFuncionario(Integer idFuncionario){
       // mostrarFuncionarioPeloId(idFuncionario);
        funcionarioRepository.deleteById(idFuncionario);
    }

    public Funcionario editarFuncionario(Funcionario funcionario){
      return funcionarioRepository.save(funcionario);
    }

    public Funcionario adicionarFoto(Integer idFuncionario, String caminhoFoto) {

        Funcionario funcionario = mostrarFuncionarioPeloId(idFuncionario);
        funcionario.setFoto(caminhoFoto);
        return funcionarioRepository.save(funcionario);
    }

}
