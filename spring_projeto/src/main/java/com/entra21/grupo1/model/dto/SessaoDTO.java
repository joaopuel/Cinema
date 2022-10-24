package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoDTO {
    private Long id;
    private String nomeFilme;
    private LocalDateTime dataSessao;
    private Double valorInteira;
    private Double taxaVip;
    private String tipoSessao;
    private Long idSala;
    private String nomeSala;
    private Long idCinema;
    private String nomeCinema;
    private String logradouroCinema;
    private Integer numeroCinema;
}
