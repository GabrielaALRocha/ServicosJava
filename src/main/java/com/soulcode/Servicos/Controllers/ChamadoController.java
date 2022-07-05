package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.Chamado;
import com.soulcode.Servicos.Services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    @GetMapping("/chamados")
    public List<Chamado> mostrarChamados(){
        List<Chamado> chamados = chamadoService.mostrarTodosChamados();
        return chamados;
    }
    @GetMapping("/chamados/{idChamado}")
    public ResponseEntity<Chamado> mostrarUmChamadoPeloId(@PathVariable Integer idChamado) {
        Chamado chamado = chamadoService.mostrarChamadoPeloId(idChamado);
        return ResponseEntity.ok().body(chamado);
    }

    @GetMapping("/chamadosPeloCliente/{idCliente}")
    public List<Chamado> buscarChamadosPeloCliente(@PathVariable Integer idCliente) {
        List<Chamado> chamados = chamadoService.buscarChamadoPeloCliente(idCliente);
        return chamados;
    }

    @GetMapping("/chamadosPeloFuncionario/{idFuncionario}")
    public List<Chamado> buscarChamadosPeloFuncionario(@PathVariable Integer idFuncionario) {
        List<Chamado> chamados = chamadoService.buscarChamadoPeloFuncionario(idFuncionario);
        return chamados;
    }

    @GetMapping("/chamadosPeloStatus")
    public List<Chamado> buscarChamadosPeloStatus(@RequestParam("status") String status) {
        List<Chamado> chamados = chamadoService.buscarChamadosPeloStatus(status);
        return chamados;
    }

    @GetMapping("/chamadosPorIntervaloData")
    public List<Chamado> buscarPorIntervaloData(@RequestParam("data1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data1,
                                                @RequestParam("data2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data2){
        List<Chamado> chamados = chamadoService.buscarPorIntervaloData(data1,data2);
        return chamados;
    }

    // aqui vamos definir o endpoint para o serviço de cadastro de um novo chamado.
    // para cadastro precisamos anotar como método http - post

    @PostMapping("/chamados/{idCliente}")
    public ResponseEntity<Chamado> cadastrarChamado(@PathVariable Integer idCliente, @RequestBody Chamado chamado){
        chamado = chamadoService.cadastrarChamado(chamado,idCliente);
        //nesse momento o chamado já foi cadastrado no database
        //precisamos agora criar o caminho (uri) para que esse novo chamado possa ser acessado
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(chamado.getIdChamado()).toUri();
        return ResponseEntity.created(novaUri).body(chamado);
    }
    // vamos mapear o serviço de excluir um chamado

    @DeleteMapping("/chamados/{idChamado}")
    public ResponseEntity<Void> excluirChamado(@PathVariable Integer idChamado){
        chamadoService.excluirChamado(idChamado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/chamados/{idChamado}")
    public ResponseEntity<Chamado> editarChamado (@PathVariable Integer idChamado, @RequestBody Chamado chamado ) {
        chamado.setIdChamado(idChamado);
        chamadoService.editarChamado(chamado, idChamado);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/chamadosAtribuirFuncionario/{idChamado}/{idFuncionario}")
    public ResponseEntity<Chamado> atribuirFuncionario (@PathVariable Integer idChamado, @PathVariable Integer idFuncionario ) {

            chamadoService.atribuirFuncionario(idChamado, idFuncionario);
            return ResponseEntity.ok().build();
    }

    @PutMapping("/chamadosModificarStatus/{idChamado}")
    public ResponseEntity<Chamado> modificarStatus(@PathVariable Integer idChamado,
                                                   @RequestParam("status") String status){
        chamadoService.modificarStatus(idChamado,status);
        return ResponseEntity.noContent().build();
    }


}
