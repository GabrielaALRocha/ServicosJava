package com.soulcode.Servicos.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Chamado {

    @Id
    private Integer idChamado;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = true)
    private String descricao;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private Date dataChamado;


    private StatusChamado status;

    @ManyToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name ="idCliente")
    private Cliente cliente;

    public Integer getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Integer idChamado) {
        this.idChamado = idChamado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataChamado() {
        return dataChamado;
    }

    public void setDataChamado(Date dataChamado) {
        this.dataChamado = dataChamado;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
