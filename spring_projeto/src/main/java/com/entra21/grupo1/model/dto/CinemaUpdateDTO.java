package com.entra21.grupo1.model.dto;

import lombok.Data;

@Data
public class CinemaUpdateDTO {
    private Long id;
    private String nome;
    private Long administrador;
    private Double caixa;
}
