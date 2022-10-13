package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvaliacaoDTO {
    private Long id;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private Double nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;
}
