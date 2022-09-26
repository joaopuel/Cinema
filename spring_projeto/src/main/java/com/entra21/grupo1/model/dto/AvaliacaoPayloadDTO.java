package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvaliacaoPayloadDTO {
    private Double rating;
    private String comentario;
    private LocalDate dataAvaliacao;
    private String nomeUsuario;
    private String nomeFilme;
}
