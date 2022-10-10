package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CinemaDTOWithDetails {
    private Long id;
    private String nome;
    private Long idAdministrador;
    private Double caixa;
    private String logradouro;
    private Integer numero;
    private List<SalaDTO> salas;
    private List<RegistroCaixaDTO> registroCaixa;
}