package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoDTO {
    private Long id;
    private LocalDateTime dataSessao;
    private Long idSala;
    private String valorInteira;
    private String valorMeia;
    private String tipoSessao;
}
