package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@Data
public class SessaoPayLoadDTO {
    private LocalDateTime dataSessao;
    private Long idFilme;
    private Long idSala;
    private String valorInteira;
    private String valorMeia;
    private String tipoSessao;
}
