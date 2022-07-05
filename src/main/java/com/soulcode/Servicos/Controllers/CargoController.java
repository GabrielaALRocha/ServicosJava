package com.soulcode.Servicos.Controllers;


import com.soulcode.Servicos.Models.Cargo;

import com.soulcode.Servicos.Services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class CargoController {

    @Autowired
    CargoService cargoService;

    @GetMapping("/cargos")
    public List<Cargo> mostrarCargo(){
        List<Cargo> cargos = cargoService.mostrarTodosCargos();
        return cargos;
    }
    @GetMapping("/cargos/{idCargo}")
    public ResponseEntity<Cargo> mostrarUmChamadoPeloId(@PathVariable Integer idCargo) {
        Cargo cargo = cargoService.mostrarCargoPeloId(idCargo);
        return ResponseEntity.ok().body(cargo);
    }

    @PostMapping("/cargos")
    public ResponseEntity<Cargo> cadastrarCargo(@RequestBody Cargo cargo) {
       cargo = cargoService.cadastrarCargo(cargo);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getIdCargo()).toUri();

        return ResponseEntity.created(novaUri).body(cargo);

    }

    @PutMapping("/cargos/{idCargo}")
    public ResponseEntity<Cargo> editarCargo(@PathVariable Integer idCargo, @RequestBody Cargo cargo) {
        cargo.setIdCargo(idCargo);
        cargoService.editarCargo(cargo);
        return ResponseEntity.ok().body(cargo);
    }

    @DeleteMapping("/cargos/{idCargo}")
    public ResponseEntity<Void> deletarCargo(@PathVariable Integer idCargo) {
        cargoService.deletarCargo(idCargo);
        return ResponseEntity.noContent().build();
    }

}
