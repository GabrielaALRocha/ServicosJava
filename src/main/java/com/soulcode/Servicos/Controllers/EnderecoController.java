package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.Cargo;
import com.soulcode.Servicos.Models.Endereco;
import com.soulcode.Servicos.Services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/enderecos")
    public List<Endereco> mostrarEndereco(){
        List<Endereco> enderecos = enderecoService.mostrarTodosEnderecos();
        return enderecos;
    }

    @GetMapping("/enderecos/{idEndereco}")
    public ResponseEntity<Endereco> mostrarUmEnderecoPeloId(@PathVariable Integer idEndereco) {
        Endereco endereco = enderecoService.mostrarEnderecoPeloId(idEndereco);
        return ResponseEntity.ok().body(endereco);
    }

    @PutMapping("/enderecos/{idEndereco}")
    public ResponseEntity<Endereco> editarCargo(@PathVariable Integer idEndereco, @RequestBody Endereco endereco) {
        endereco.setIdEndereco(idEndereco);
        enderecoService.editarEndereco(endereco);
        return ResponseEntity.ok().body(endereco);
    }

    @DeleteMapping("/enderecos/{idEndereco}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer idEndereco) {
        enderecoService.deletarEndereco(idEndereco);
        return ResponseEntity.noContent().build();
    }
}
