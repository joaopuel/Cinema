package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IngressoDTO {
    private Long id;
    private LocalDateTime dataCompra;
    private Long idPessoa;
    private String nomeFilme;
    private String nomeCinema;
    private Double valorIngresso;
    private Boolean meiaEntrada;
    private Boolean cadeiraVip;
    private SessaoDTO sessao;
    private CadeiraDTO cadeira;
}
