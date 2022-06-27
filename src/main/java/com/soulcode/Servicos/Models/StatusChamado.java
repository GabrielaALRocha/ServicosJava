package com.soulcode.Servicos.Models;

public enum StatusChamado {

    RECEBIDO("Recebido"),
    ATRIBUIDO("Atribuido"),
    CONCLUIDO("Concluido"),
    ARQUIVADO("Arquivado");

    private String tipo;

    StatusChamado(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
