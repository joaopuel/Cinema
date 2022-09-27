package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double saldoCarteira;
    private String login;
    private String senha;
}