package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoDTOWithDetails {
    private Long id;
    private LocalDateTime dataSessao;
    private String valorInteira;
    private String valorMeia;
    private String tipoSessao;
    private SalaDTO sala;
}
