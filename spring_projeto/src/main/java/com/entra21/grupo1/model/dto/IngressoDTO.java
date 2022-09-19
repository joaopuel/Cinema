package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IngressoDTO {
    private Long idSessao;
    private Long idPessoa;
    private Long idCadeira;
    private LocalDateTime dataCompra;
}
