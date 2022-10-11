package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoDTOWithDetails {
    private Long id;
    private String nomeFilme;
    private LocalDateTime dataSessao;
    private Double valorInteira;
    private Double taxaVip;
    private String tipoSessao;
    private SalaDTO sala;
    private Long idCinema;
    private String nomeCinema;
}
