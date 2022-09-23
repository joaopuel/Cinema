package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class IngressoDTO {
    private SessaoDTO sessao;
    private CadeiraDTO cadeira;
    private LocalDateTime dataCompra;
}
