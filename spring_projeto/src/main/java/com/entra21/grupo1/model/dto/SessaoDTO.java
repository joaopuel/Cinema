package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoDTO {
    private Long id;
    private LocalDateTime dataSessao;
    private Double valorInteira;
    private Double valorMeia;
    private String tipoSessao;
    private Long idSala;
}
