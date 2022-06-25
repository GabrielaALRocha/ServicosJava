package com.soulcode.Servicos.Repositories;

import com.soulcode.Servicos.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Integer> {

    Optional<Funcionario> findByEmail(String email);

    // Optional<Funcionario> finByNomeAndEmail(String nome, String email);
}
