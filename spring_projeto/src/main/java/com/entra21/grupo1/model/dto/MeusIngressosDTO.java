package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeusIngressosDTO {
    //ingresso
    private Long id;
    private LocalDateTime dataCompra;

    //cadeira
    private String codigo;
    private String tipoCadeira;
    private Integer fileira;
    private Integer ordemFileira;

    //sala
    private String nomeSala;

    //cinema
    private String nomeCinema;

    //sessao
    private LocalDateTime dataSessao;
    private Double valorInteira;
    private Double valorMeia;
    private String tipoSessao;

    //filme
    private String nomeFilme;
}
