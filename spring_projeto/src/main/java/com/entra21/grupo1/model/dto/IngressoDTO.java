package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IngressoDTO {
    //ingresso
    private Long id;
    private LocalDateTime dataCompra;

    //Usuário
    private Long idPessoa;

    //cadeira
    private CadeiraDTO cadeira;

    //cinema
    private String nomeCinema;

    //sessao
    private SessaoDTO sessao;

    //filme
    private String nomeFilme;
}
