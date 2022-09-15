package com.entra21.grupo1.model.dto;

import lombok.Data;

@Data
public class CinemaDTO {
    private Long id;
    private String nome;
    private PessoaDTO administrador;
    private Double caixa;
}