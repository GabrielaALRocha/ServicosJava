package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.*;
import com.soulcode.Servicos.Repositories.CargoRepository;
import com.soulcode.Servicos.Repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
//    FuncionarioRepository funcionarioRepository;

    public List<Cargo> mostrarTodosCargos(){

        return cargoRepository.findAll();
    }

    public Cargo mostrarCargoPeloId(Integer idCargo) {
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        return cargo.orElseThrow();
    }

    public Cargo cadastrarCargo(Cargo cargo){
        cargo.setIdCargo(null);
        return cargoRepository.save(cargo);
    }

    public Cargo editarCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public void deletarCargo(Integer idCargo) {
        cargoRepository.deleteById(idCargo);
    }

}
