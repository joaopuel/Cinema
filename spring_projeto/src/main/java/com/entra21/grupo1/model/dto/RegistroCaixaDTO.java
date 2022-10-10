package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroCaixaDTO {
    private Long id;
    private String operacao;
    private Double valor;
    private String descricao;
    private String nomeCinema;
    private LocalDateTime dataOperacao;
}