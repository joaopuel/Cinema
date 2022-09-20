package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CinemaPayloadDTO {
    private String nome;
    private Long administrador;
    private Double caixa;
}
