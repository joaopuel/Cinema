package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class FilmeDTO {
    private Long id;
    private String nome;
    private LocalTime duracao;
    private String sinopse;
    private String diretor;
    private String cartaz;
    private List<SessaoDTO> sessoes;
}
