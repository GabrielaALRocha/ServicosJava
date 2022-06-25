package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.Cliente;
import com.soulcode.Servicos.Repositories.ClienteRepository;
import com.soulcode.Servicos.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> mostrarClientes() {
        List<Cliente> clientes = clienteService.mostrarTodosCliente();
        return clientes;
    }

    @GetMapping("/clientes/{idCliente}")
        public ResponseEntity<Cliente> mostrarUmFuncionarioPeloId(@PathVariable Integer idCliente) {
            Cliente cliente = clienteService.mostrarClientePeloId(idCliente);
            return ResponseEntity.ok().body(cliente);
        }

    @GetMapping("/clientesEmail/{emailCliente}")
    public ResponseEntity<Cliente> mostrarUmFuncionarioPeloEmail(@PathVariable String emailCliente) {
        Cliente cliente = clienteService.mostrarClientePeloEmail(emailCliente);
        return ResponseEntity.ok().body(cliente);
    }


    @PostMapping("/clientes")
    public ResponseEntity<Cliente> cadastrarClientes(@RequestBody Cliente clientes) {
        clientes = clienteService.cadastrarCliente(clientes);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(clientes.getIdCliente()).toUri();
        return ResponseEntity.created(novaUri).body(clientes);
    }

    @DeleteMapping("/clientes/{idCliente}")
    public ResponseEntity <Void> deletarFuncionario (@PathVariable Integer idCliente) {
        clienteService.excluirCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clientes/{idCliente}")
    public ResponseEntity <Cliente> alterarFuncionario(@PathVariable Integer idCliente, @RequestBody Cliente cliente){
        cliente.setIdCliente(idCliente);
        clienteService.editarCliente(cliente);
        return ResponseEntity.noContent().build();
    }


}
