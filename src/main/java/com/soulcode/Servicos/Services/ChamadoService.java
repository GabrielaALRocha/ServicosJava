package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.Chamado;
import com.soulcode.Servicos.Models.Cliente;
import com.soulcode.Servicos.Models.Funcionario;
import com.soulcode.Servicos.Models.StatusChamado;
import com.soulcode.Servicos.Repositories.ChamadoRepository;
import com.soulcode.Servicos.Repositories.ClienteRepository;
import com.soulcode.Servicos.Repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;



    public List<Chamado> mostrarTodosChamados(){

        return chamadoRepository.findAll();
    }


    public Chamado mostrarChamadoPeloId(Integer idChamado) {
        Optional<Chamado> chamado = chamadoRepository.findById(idChamado);
        return chamado.orElseThrow();
    }

    public List<Chamado> buscarChamadoPeloCliente(Integer idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        return chamadoRepository.findByCliente(cliente);
    }

    public List<Chamado> buscarChamadoPeloFuncionario(Integer idFuncionario) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
        return chamadoRepository.findByFuncionario(funcionario);
    }

    public List<Chamado> buscarChamadosPeloStatus(String status) {
        return chamadoRepository.findByStatus(status);
    }

    public List<Chamado> buscarPorIntervaloData(Date data1, Date data2){
        return chamadoRepository.findByIntervaloData(data1,data2);
    }

    //cadastrar um novo chamado; temos 2 regras: 1º no momento do cadastro do chamado, já devemos informar de qual cliente é / no momento do cadastro do chamado, a princípio  vamos fazer esse cadastro sem estar atribuido a um funcionário  / no momento do castro do chamado, o status desse chamado deve ser RECEBIDO

    //serviço para cadastro de novo chamado

    public Chamado cadastrarChamado(Chamado chamado, Integer idCliente){
        // regra 3 - atribuição do status recebido para o chamado que está sendo cadastrado
        chamado.setStatus(StatusChamado.RECEBIDO);
        //regra 2 - dizer que ainda não atribuimos esse chamado pra nenhum funcionário
        chamado.setFuncionario(null);
        //regra 1 - buscando os dados do cliente dono do chamado
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        chamado.setCliente(cliente.get());
        return chamadoRepository.save(chamado);
    }

    // Método para exclusão de um chamado

    public void excluirChamado(Integer idChamado){
        chamadoRepository.deleteById(idChamado);
    }

    public Chamado editarChamado(Chamado chamado, Integer idChamado) {
        Chamado chamadoAnterior = mostrarChamadoPeloId(idChamado);
        Funcionario funcionario = chamadoAnterior.getFuncionario();
        Cliente cliente = chamadoAnterior.getCliente();

        chamado.setCliente(cliente);
        chamado.setFuncionario(funcionario);
        return chamadoRepository.save(chamado);
    }

    public Chamado atribuirFuncionario(Integer idChamado, Integer idFuncionario) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
        Chamado chamado = mostrarChamadoPeloId(idChamado);
        chamado.setFuncionario(funcionario.get());
        chamado.setStatus(StatusChamado.RECEBIDO);

        return chamadoRepository.save(chamado);
    }

    public Chamado modificarStatus(Integer idChamado, String status) {
        Chamado chamado = mostrarChamadoPeloId(idChamado);

        if(chamado.getFuncionario() != null){

        switch (status) {
            case "ATRIBUIDO": {
                chamado.setStatus(StatusChamado.ATRIBUIDO);
                break;
            }
            case "CONCLUIDO": {
                chamado.setStatus(StatusChamado.CONCLUIDO);
                break;
            }
            case "ARQUIVADO": {
                chamado.setStatus(StatusChamado.ARQUIVADO);
                break;
            }
        }
            switch (status){



            case "RECEBIDO":
            {
                chamado.setStatus(StatusChamado.RECEBIDO);
                break;
            }
        }
        }

            return chamadoRepository.save(chamado);
    }

}
